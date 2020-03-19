/**
 * 
 */
package org.yelong.support.servlet.wrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.yelong.support.servlet.HttpServletUtils;

/**
 * @author 彭飞
 * @date 2019年9月20日下午2:58:27
 * @version 1.2
 */
public class HttpServletRequestReuseWrapper extends HttpServletRequestWrapper{

	private final byte [] body;

	private String charset;

	public HttpServletRequestReuseWrapper(HttpServletRequest request) throws IOException {
		this(request,null);
	}

	public HttpServletRequestReuseWrapper(HttpServletRequest request,String charset) throws IOException {
		super(request);
		if(StringUtils.isNotBlank(charset)) {
			this.charset = charset;
			request.setCharacterEncoding(charset);
		}
		body = readerBody(request);
	}

	@Override		
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new BufferedServletInputStream(body);
	}

	/**
	 * 获取请求消息体
	 * @author 彭飞
	 * @date 2019年9月20日下午3:28:10
	 * @version 1.2
	 * @return
	 */
	public byte[] getBody() {
		return body;
	}

	/**
	 * 获取请求消息体的字符串
	 * @author 彭飞
	 * @date 2019年12月6日上午11:00:12
	 * @version 1.0
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getBodyStr() throws UnsupportedEncodingException {
		if(StringUtils.isBlank(charset)) {
			return new String(body);
		} else {
			return new String(body,charset);
		}
	}

	/**
	 * 读取消息体
	 * @author 彭飞
	 * @date 2019年12月6日上午11:01:49
	 * @version 1.0
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static final byte [] readerBody(HttpServletRequest request) throws IOException {
		return readerBody(request, null);
	}

	/**
	 * 读取消息体
	 * @author 彭飞
	 * @date 2019年12月6日上午11:01:56
	 * @version 1.0
	 * @param request
	 * @param charset request.setCharacterEncoding(charset);
	 * @return
	 * @throws IOException
	 */
	public static final byte [] readerBody(HttpServletRequest request,String charset) throws IOException {
		if(StringUtils.isNotBlank(charset)) {
			request.setCharacterEncoding(charset);
		}
		if( isHttpServletRequestReuseWrapper(request) ) {
			return getHttpServletRequestReuseWrapper(request).getBody();
		} else {
			return HttpServletUtils.readerBody(request);
		}
	}

	/**
	 * 读取请求消息体字符
	 * @author 彭飞
	 * @date 2019年12月6日上午11:03:11
	 * @version 1.0
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static final String readerBodyStr(HttpServletRequest request) throws IOException {
		return readerBodyStr(request, null);
	}

	/**
	 * 读取请求消息体字符
	 * @author 彭飞
	 * @date 2019年12月6日上午11:03:40
	 * @version 1.0
	 * @param request
	 * @param charset
	 * @return
	 * @throws GetHttpServletRequestReuseWrapperException 
	 * @throws  
	 * @throws IOException
	 */
	public static final String readerBodyStr(HttpServletRequest request,String charset) throws IOException {
		if( isHttpServletRequestReuseWrapper(request) ) {
			return getHttpServletRequestReuseWrapper(request).getBodyStr();
		} else {
			return HttpServletUtils.readerBodyStr(request);
		}
	}

	/**
	 * 判断request是否被HttpServletRequestReuseWrapper包装
	 * @author 彭飞
	 * @date 2019年12月6日上午11:09:55
	 * @version 1.0
	 * @param request
	 * @return
	 */
	public static boolean isHttpServletRequestReuseWrapper(HttpServletRequest request) {
		try {
			getHttpServletRequestReuseWrapper(request);
			return true;
		} catch (GetHttpServletRequestReuseWrapperException e) {
			return false;
		}
	}
	
	/**
	 * 如果request被HttpServletRequestReuseWrapper包装。或者反复包装的HttpServletRequestReuseWrapper对象。
	 * @author 彭飞
	 * @date 2019年12月6日上午11:08:28
	 * @version 1.0
	 * @param request
	 * @return
	 * @throws GetHttpServletRequestReuseWrapperException 如果request没有被HttpServletRequestReuseWrapper对象进行包装
	 */
	public static HttpServletRequestReuseWrapper getHttpServletRequestReuseWrapper(HttpServletRequest request) throws GetHttpServletRequestReuseWrapperException {
		while(true) {
			if( request instanceof HttpServletRequestReuseWrapper ) {
				return ((HttpServletRequestReuseWrapper)request);
			} else if( request instanceof HttpServletRequestWrapper) {
				request = (HttpServletRequest) ((HttpServletRequestWrapper)request).getRequest();
				continue;
			} else {
				break;
			}
		}
		throw new GetHttpServletRequestReuseWrapperException(request);
	}
	
	
}
