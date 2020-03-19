/**
 * 
 */
package org.yelong.support.spring.mvc.token;

/**
 * 无效的token
 * @author 彭飞
 * @date 2019年8月30日下午3:17:44
 * @version 1.2
 */
public class InvalidTokenException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2221122150113780941L;

	private final String token;
	
	
	public InvalidTokenException(String token) {
		this.token = token;
	}
	
	public InvalidTokenException(String token , String message) {
		super(message);
		this.token = token;
	}

	public String getToken() {
		return token;
	}
	
	
}
