/**
 * 
 */
package org.yelong.support.servlet.filter.security;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.yelong.support.servlet.wrapper.BufferedServletInputStream;
import org.yelong.support.servlet.wrapper.HttpServletRequestReuseWrapper;

/**
 * 解密的request包装器
 * @author 彭飞
 * @date 2019年9月17日下午12:15:26
 * @version 1.2
 */
public class SecurityHttpServletRequestWrapper extends HttpServletRequestReuseWrapper implements HttpServletRequestSecurity{

	private Map<String,String []> parameterMap;
	
	private boolean parameterMapDecrypt = false;
	
	private byte [] body;
	
	private boolean bodyDecrypt = false ;
	
	public SecurityHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		body = super.getBody();
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		if( !parameterMapDecrypt ) {
			return getSourceParameterMap();
		}
		return this.parameterMap;
	}

	/**
	 * 设置解密后的参数映射
	 * @author 彭飞
	 * @date 2019年9月17日下午3:46:55
	 * @version 1.2
	 * @param parameterMap
	 */
	public void setAfterDecryptParameterMap(Map<String, String[]> parameterMap) {
		this.parameterMap = parameterMap;
		this.parameterMapDecrypt = true;
	}
	
	/**
	 * 设置解密后的请求消息体
	 * @author 彭飞
	 * @date 2019年9月17日下午3:44:42
	 * @version 1.2
	 * @param body 解密后的请求消息体
	 */
	public void setAfterDecryptBody(byte [] body) {
		this.body = body;
		this.bodyDecrypt = true;
	}
	
	
	public byte[] getBody() {
		if( !bodyDecrypt ) {
			return getSourceBody();
		}
		return body;
	}
	
	@Override
	public String getParameter(String name) {
		if(!parameterMapDecrypt) {
			return getSourceParameter(name);
		}
		String [] value = this.parameterMap.get(name);
		if( null == value ) {
			return null;
		} else if( value.length == 0 ) {
			return null;
		}
		return value[0];
	}
	
	@Override
	public String[] getParameterValues(String name) {
		if(!parameterMapDecrypt) {
			return getSourceParameterValues(name);
		}
		return parameterMap.get(name);
	}
	
	@Override
	public Enumeration<String> getParameterNames() {
		if(!parameterMapDecrypt) {
			return getSourceParameterNames();
		}
		return new Vector<String>( parameterMap.keySet()).elements();
	}
	
	@Override
	public Map<String, String[]> getSourceParameterMap() {
		return super.getParameterMap();
	}

	@Override
	public String getSourceParameter(String name) {
		return super.getParameter(name);
	}

	@Override
	public String[] getSourceParameterValues(String name) {
		return super.getParameterValues(name);
	}

	@Override
	public byte [] getSourceBody() {
		return super.getBody();
	}
	
	@Override
	public Enumeration<String> getSourceParameterNames() {
		return super.getParameterNames();
	}
	
	@Override
	public ServletInputStream getInputStream() throws IOException {
		if( !bodyDecrypt ) {
			return super.getInputStream();
		}
		return new BufferedServletInputStream(body);
	}
}
