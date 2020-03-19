/**
 * 
 */
package org.yelong.support.servlet.filter.security;

import java.util.Enumeration;
import java.util.Map;

/**
 * 支持参数解密的request<br/>
 * @author 彭飞
 * @date 2019年9月17日下午3:32:09
 * @version 1.2
 */
public interface HttpServletRequestSecurity{

	/**
	 * 获取源参数映射
	 * @author 彭飞
	 * @date 2019年9月17日下午3:39:51
	 * @version 1.2
	 * @return
	 */
	Map<String,String []> getSourceParameterMap();
	
	/**
	 * 获取源参数值
	 * @author 彭飞
	 * @date 2019年9月17日下午3:40:06
	 * @version 1.2
	 * @param name
	 * @return
	 */
	String getSourceParameter(String name);
	
	/**
	 * @author 彭飞
	 * @date 2019年9月17日下午3:40:14
	 * @version 1.2
	 * @param name
	 * @return
	 */
	String[] getSourceParameterValues(String name);
	
	/**
	 * 获取源请求消息体
	 * @author 彭飞
	 * @date 2019年9月17日下午3:41:28
	 * @version 1.2
	 * @return
	 */
	byte[] getSourceBody();
	
	/**
	 * 获取源所有参数名称
	 * @author 彭飞
	 * @date 2019年9月17日下午3:51:17
	 * @version 1.2
	 * @return
	 */
	Enumeration<String> getSourceParameterNames();
	
}
