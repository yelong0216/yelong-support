/**
 * 
 */
package org.yelong.support.spring.mvc.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 抽象mvc拦截器。
 * 提供一些拦截器常用的方法
 * @author 彭飞
 * @date 2019年8月7日上午11:46:17
 * @version 1.0
 */
public abstract class AbstractHandlerInterceptor extends HandlerInterceptorAdapter{

	protected static final String DEFAULT_RESPONSE_CONTENT_TYPE = "text/html;charset=UTF-8";

	
	/**
	 * 使用默认的contentType响应信息<br/>
	 * {@link #DEFAULT_RESPONSE_CONTENT_TYPE}
	 * @author 彭飞
	 * @date 2019年8月7日上午11:51:12
	 * @version 1.0
	 * @param response http响应对象
	 * @param message 响应消息
	 * @throws IOException
	 */
	@Deprecated
	protected void writer(HttpServletResponse response , String message ) throws IOException {
		writer(response, message, DEFAULT_RESPONSE_CONTENT_TYPE);
	}

	/**
	 * 使用指定的contentType响应数据<br/>
	 * @author 彭飞
	 * @date 2019年8月7日上午11:51:42
	 * @version 1.0
	 * @param response http响应对象
	 * @param message 响应消息
	 * @param contentType 文本类型
	 * @throws IOException
	 */
	@Deprecated
	protected void writer(HttpServletResponse response , String message ,String contentType) throws IOException {
		response.setContentType(contentType);
		PrintWriter out = response.getWriter();
		out.write(message);
	}

}
