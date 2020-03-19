/**
 * 
 */
package org.yelong.support.servlet.wrapper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pengfei<yl1430834495@163.com>
 * @date 2019年12月6日上午11:06:27
 * @version 1.0
 */
public class GetHttpServletRequestReuseWrapperException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3507293943349953479L;
	
	public GetHttpServletRequestReuseWrapperException(String message) {
		super(message);
	}
	
	public GetHttpServletRequestReuseWrapperException(HttpServletRequest request) {
		this(request+"对象没有被HttpServletRequestReuseWrapper类进行包装！");
	}
	

}
