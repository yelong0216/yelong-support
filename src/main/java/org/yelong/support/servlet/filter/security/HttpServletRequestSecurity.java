/**
 * 
 */
package org.yelong.support.servlet.filter.security;

import java.util.Enumeration;
import java.util.Map;

/**
 * 支持参数解密的request<br/>
 * 
 * @author PengFei
 */
public interface HttpServletRequestSecurity{

	/**
	 * 获取源参数映射
	 * 
	 * @return 源参数映射
	 */
	Map<String,String []> getSourceParameterMap();
	
	/**
	 * 获取源参数值
	 * 
	 * @param name key
	 * @return 源参数值
	 */
	String getSourceParameter(String name);
	
	/**
	 * 获取源参数值
	 * 
	 * @param name key
	 * @return 源参数值
	 */
	String[] getSourceParameterValues(String name);
	
	/**
	 * 获取源请求消息体
	 * 
	 * @return request body
	 */
	byte[] getSourceBody();
	
	/**
	 * 获取源所有参数名称
	 * 
	 * @return 源所有参数名称
	 */
	Enumeration<String> getSourceParameterNames();
	
}
