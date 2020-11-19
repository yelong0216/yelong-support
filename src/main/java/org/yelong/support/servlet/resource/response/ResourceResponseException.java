package org.yelong.support.servlet.resource.response;

import javax.servlet.ServletException;

/**
 * 资源响应异常
 * 
 * @since 2.2
 */
public class ResourceResponseException extends ServletException {

	private static final long serialVersionUID = 8392056094498836244L;

	public ResourceResponseException() {
		super();
	}

	public ResourceResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceResponseException(String message) {
		super(message);
	}

	public ResourceResponseException(Throwable cause) {
		super(cause);
	}

}
