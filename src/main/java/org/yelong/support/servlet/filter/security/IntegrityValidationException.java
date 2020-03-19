/**
 * 
 */
package org.yelong.support.servlet.filter.security;

/**
 * 完整性效验异常
 * @author 彭飞
 * @date 2019年9月17日下午3:26:50
 * @version 1.2
 */
public class IntegrityValidationException extends RuntimeException{

	private static final long serialVersionUID = -9022584964419053329L;

	public IntegrityValidationException(String message) {
		super(message);
	}
	
	public IntegrityValidationException(Throwable t) {
		super(t);
	}
	
	public IntegrityValidationException() {
		
	}
	
}
