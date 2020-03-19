/**
 * 
 */
package org.yelong.support.orm.mybaits.sql;

/**
 * @author 彭飞
 * @date 2019年11月1日上午10:00:31
 * @version 1.2
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
