/**
 * 
 */
package org.yelong.support.orm.mybaits.sql;

import org.yelong.core.jdbc.sql.SqlFragmentException;

/**
 * @since 1.0
 */
public class MyBatisSqlFragmentException extends SqlFragmentException {

	private static final long serialVersionUID = 76277246096022753L;

	public MyBatisSqlFragmentException() {
		super();
	}

	public MyBatisSqlFragmentException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyBatisSqlFragmentException(String message) {
		super(message);
	}

	public MyBatisSqlFragmentException(Throwable cause) {
		super(cause);
	}

}
