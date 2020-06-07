/**
 * 
 */
package org.yelong.support.servlet.filter.security;

/**
 * 解密异常
 * 
 * @author PengFei
 */
public class SecurityException extends Exception{

	private static final long serialVersionUID = 5997953985029306996L;
	
	public SecurityException(String message) {
		super(message);
	}
	
	public SecurityException(Throwable t) {
		super(t);
	}
	
	public SecurityException() {
		
	}

}
