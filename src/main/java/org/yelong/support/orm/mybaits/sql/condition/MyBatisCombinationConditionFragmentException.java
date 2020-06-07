/**
 * 
 */
package org.yelong.support.orm.mybaits.sql.condition;


/**
 * mybatis 组合条件片段异常
 */
public class MyBatisCombinationConditionFragmentException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2784436114375320792L;
	
	public MyBatisCombinationConditionFragmentException() {
		
	}
	
	public MyBatisCombinationConditionFragmentException(String message) {
		super(message);
	}
	
	public MyBatisCombinationConditionFragmentException(Throwable t) {
		super(t);
	}

}
