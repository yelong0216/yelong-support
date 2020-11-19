package org.yelong.support.servlet.resource.response.responder;

import javax.servlet.http.HttpServletRequest;

/**
 * 为一个单独的资源定制响应方式。一般用在为一个特殊的文件进行特殊的处理时使用。
 * 
 * @since 2.2
 */
public interface CustomResourceResponder extends ResourceResponder {

	/**
	 * 获取请求这个资源的URI。用来区分这个资源的标识
	 * 
	 * @return 请求该资源的URI
	 * @see HttpServletRequest#getRequestURI()
	 */
	String getRequestURI();

}
