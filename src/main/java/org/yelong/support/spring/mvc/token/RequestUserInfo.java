/**
 * 
 */
package org.yelong.support.spring.mvc.token;

/**
 * 请求的用户信息
 * 
 * @since 1.0
 */
public interface RequestUserInfo {

	/**
	 * 获取请求token
	 * 
	 * @return token
	 */
	String getToken();

	/**
	 * 根据key获取用户信息
	 * 
	 * @param key key
	 * @return value
	 */
	Object get(String key);

	/**
	 * 获取所有的用户key
	 * 
	 * @return 所有的key
	 */
	String[] getKeys();

}
