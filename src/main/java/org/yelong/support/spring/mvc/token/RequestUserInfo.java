/**
 * 
 */
package org.yelong.support.spring.mvc.token;

/**
 * 请求的用户信息
 * @author PengFei
 */
public interface RequestUserInfo {

	/**
	 * 获取请求token
	 * @return
	 */
	String getToken();
	
	/**
	 * 根据key获取用户信息
	 * @param key
	 * @return
	 */
	Object get(String key);
	
	/**
	 * 获取所有的用户key
	 * @return
	 */
	String [] getKeys();
	
}
