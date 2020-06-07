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
 * 可以重复的请求包装器
 * 重复使用包含：请求消息体
 * 
 * @author PengFei
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
	 * 
	 * @return request body
	 */
	public byte[] getBody() {
		return body;
	}

	/**
	 * 获取请求消息体的字符串
	 * 
	 * @return request body str
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
	 * 
	 * @param request request
	 * @return 消息体
	 * @throws IOException 读取消息体异常
	 */
	public static final byte [] readerBody(HttpServletRequest request) throws IOException {
		return readerBody(request, null);
	}

	/**
	 * 读取消息体
	 * 
	 * @param request request
	 * @param charset request.setCharacterEncoding(charset);
	 * @return 消息体且指定编码
	 * @throws IOException 读取消息体异常
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
	 * 
	 * @param request request
	 * @return 读取请求消息体字符串
	 * @throws IOException 消息体读取异常
	 */
	public static final String readerBodyStr(HttpServletRequest request) throws IOException {
		return readerBodyStr(request, null);
	}

	/**
	 * 读取请求消息体字符
	 * 
	 * @param request request
	 * @param charset 编码
	 * @return 读取请求消息体字符串，并指定编码格式
	 * 
	 * @throws IOException 消息体读取异常
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
	 * 
	 * @param request request
	 * @return <code>true</code> 是被 HttpServletRequestReuseWrapper 包装的
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
	 * 
	 * @param request request
	 * @return HttpServletRequestReuseWrapper obj
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
