/**
 * 
 */
package org.yelong.support.spring.mvc.token;

/**
 * 请求的用户信息
 * @author 彭飞
 * @date 2019年8月30日下午3:31:44
 * @version 1.2
 */
public interface RequestUserInfo {

	/**
	 * 获取请求token
	 * @author 彭飞
	 * @date 2019年8月30日下午3:33:00
	 * @version 1.2
	 * @return
	 */
	String getToken();
	
	/**
	 * 根据key获取用户信息
	 * @author 彭飞
	 * @date 2019年8月30日下午3:32:50
	 * @version 1.2
	 * @param key
	 * @return
	 */
	Object get(String key);
	
	/**
	 * 获取所有的用户key
	 * @author 彭飞
	 * @date 2019年8月30日下午3:33:05
	 * @version 1.2
	 * @return
	 */
	String [] getKeys();
	
	
}
