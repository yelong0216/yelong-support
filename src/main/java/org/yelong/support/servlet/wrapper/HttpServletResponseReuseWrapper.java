/**
 * 
 */
package org.yelong.support.servlet.wrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 可以获取响应结果
 * 
 * 用此对象替换response后，必须在doFilter之后调用{@link #anewWriteContent(byte[])}进行重新输入响应结果
 * 
 * @since 1.0
 */
public class HttpServletResponseReuseWrapper extends HttpServletResponseWrapper {

	private ByteArrayOutputStream buffer;

	private WrapperOutputStream out;

	private PrintWriterWrapper writer;

	private boolean isOut = false;

	private boolean isWriter = false;

	// 此response为非HttpServletResponseReuseWrapper对象的response
	private final HttpServletResponse sourceResponse;

	// 是否包装的自己
	private boolean isWrapperThis = false;

	private boolean isResponseContent = false;

	public HttpServletResponseReuseWrapper(HttpServletResponse response) {
		super(response);
		if (response instanceof HttpServletResponseReuseWrapper) {
			HttpServletResponseReuseWrapper responseWrapper = (HttpServletResponseReuseWrapper) response;
			isWrapperThis = true;
			this.buffer = responseWrapper.buffer;
			this.out = responseWrapper.out;
			this.writer = responseWrapper.writer;
			this.sourceResponse = responseWrapper.sourceResponse;
		} else {
			this.buffer = new ByteArrayOutputStream();
			this.out = new WrapperOutputStream(buffer);
			this.sourceResponse = response;
			this.writer = new PrintWriterWrapper(out);
		}
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (isWrapperThis) {
			return getResponse().getOutputStream();
		}
		if (isWriter) {
			throw new IOException("调用getWriter之后无法再次调用getOutputStream");
		} else {
			this.isOut = true;
		}
		return out;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (isWrapperThis) {
			return getResponse().getWriter();
		}
		if (isOut) {
			throw new IOException("调用getOutputStream之后无法再次调用getWriter");
		} else {
			this.isWriter = true;
		}
		return writer;
	}

	@Override
	public void flushBuffer() throws IOException {
		if (isWrapperThis) {
			getResponse().flushBuffer();
			return;
		}
		if (isOut) {
			out.flush();
		} else if (isWriter) {
			writer.flush();
		}
	}

	/**
	 * 是否响应内容。 用 HttpServletResponseReuseWrapper
	 * 包装response后，所有的write、OutputStream均会缓存起来 需要调用
	 * {@link #responseContent()}方法后才会真正将响应内容写入响应中
	 * 
	 * @return <code>true</code> 是否已经响应内容
	 */
	public boolean isResponseContent() {
		if (isWrapperThis) {
			return getResponseReuseWrapper().isResponseContent;
		}
		return isResponseContent;
	}

	/**
	 * 将响应内容真正写入响应体中。 注意：此方法仅能调用一次
	 * 
	 * @throws IOException 响应失败
	 */
	public void responseContent() throws IOException {
		if (isWrapperThis) {
			getResponseReuseWrapper().responseContent();
			return;
		}
		if (isResponseContent) {
			throw new UnsupportedOperationException("已响应过内容，无法再次响应");
		}
		// anewWriteContent(getContent());
		if (isOut) {
			ServletOutputStream outputStream = sourceResponse.getOutputStream();
			byte[] content = buffer.toByteArray();
			sourceResponse.setContentLength(content.length);
			outputStream.write(content);
			outputStream.flush();
			isResponseContent = true;
		} else if (isWriter) {
			PrintWriter printWriter = sourceResponse.getWriter();
			String content = writer.getContent();
			// sourceResponse.setContentLength(content.getBytes().length);
			printWriter.write(content);
			printWriter.flush();
			isResponseContent = true;
		}
	}

	/**
	 * 获取响应内容 根据out或者writer中获取存储的响应内容
	 * 
	 * @return 响应内容
	 * @throws IOException 流异常
	 */
	public byte[] getContent() throws IOException {
		if (isWrapperThis) {
			return getResponseReuseWrapper().getContent();
		}
		if (isOut) {
			// 不调用此方法，获取不到值
			flushBuffer();
			return buffer.toByteArray();
		} else if (isWriter) {
			return this.writer.getContent().getBytes();
		}
		return null;
	}

	@Override
	public void reset() {
		if (isWrapperThis) {
			((HttpServletResponseReuseWrapper) getResponse()).reset();
		} else if (isOut()) {
			buffer.reset();
		} else if (isWriter()) {
			writer.reset();
		}
	}

	/**
	 * 获取此response包装器的原response（此response包装器可能包装了一个response包装器）
	 * 
	 * @return 获取此包装器包装的response对象
	 */
	public HttpServletResponse getSourceResponse() {
		return sourceResponse;
	}

	/**
	 * 是否调用了 {@link #getOutputStream()}
	 * 
	 * @return <tt>true</tt> 已调用
	 */
	public boolean isOut() {
		if (isWrapperThis) {
			return getResponseReuseWrapper().isOut;
		}
		return isOut;
	}

	/**
	 * 是否调用了 {@link #getWriter()}
	 * 
	 * @return <tt>true</tt> 已调用
	 */
	public boolean isWriter() {
		if (isWrapperThis) {
			return getResponseReuseWrapper().isWriter;
		}
		return isWriter;
	}

	/**
	 * 获取包装的自己
	 * 
	 * @return this
	 */
	private HttpServletResponseReuseWrapper getResponseReuseWrapper() {
		if (isWrapperThis) {
			return (HttpServletResponseReuseWrapper) getResponse();
		} else {
			return null;
		}
	}

	public class WrapperOutputStream extends ServletOutputStream {

		private ByteArrayOutputStream bos;

		public WrapperOutputStream(ByteArrayOutputStream bos) {
			this.bos = bos;
		}

		@Override
		public void write(int b) throws IOException {
			bos.write(b);
		}

		@Override
		public boolean isReady() {
			return false;

		}

		@Override
		public void setWriteListener(WriteListener arg0) {

		}

		public ByteArrayOutputStream getBos() {
			return bos;
		}

	}

}
