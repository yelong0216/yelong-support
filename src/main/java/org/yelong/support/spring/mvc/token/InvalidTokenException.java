/**
 * 
 */
package org.yelong.support.spring.mvc.token;

/**
 * @author PengFei
 */
public class InvalidTokenException extends Exception {

	private static final long serialVersionUID = 2221122150113780941L;

	private final String token;

	public InvalidTokenException(String token) {
		this.token = token;
	}

	public InvalidTokenException(String token, String message) {
		super(message);
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}
