/**
 * 
 */
package org.yelong.support.servlet.filter.security;

/**
 * 完整性效验异常
 * 
 * @since 1.0
 */
public class IntegrityValidationException extends RuntimeException {

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
