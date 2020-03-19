/**
 * 
 */
package org.yelong.support.orm.mybaits.sql;

/**
 * @author PengFei
 */
public class MyBatisSqlFragmentException extends RuntimeException{

	private static final long serialVersionUID = 76277246096022753L;

	public MyBatisSqlFragmentException() {

	}

	public MyBatisSqlFragmentException(String message) {
		super(message);
	}

	public MyBatisSqlFragmentException(Throwable t) {
		super(t);
	}

}
