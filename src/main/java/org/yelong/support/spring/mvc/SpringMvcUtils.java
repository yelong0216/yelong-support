/**
 * 
 */
package org.yelong.support.spring.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.yelong.support.spring.ApplicationContextDecorator;

/**
 * @author PengFei
 */
public final class SpringMvcUtils {
	
	private SpringMvcUtils() {}

	/**
	 * 获取请求映射的处理器方法
	 * 
	 * @param request 请求
	 * @return 处理器方法
	 * @throws Exception
	 */
	public static HandlerMethod getRequestMappingHandlerMethod(HttpServletRequest request) throws Exception {
		RequestMappingHandlerMapping requestMapping = ApplicationContextDecorator.getBean(RequestMappingHandlerMapping.class);
		HandlerExecutionChain handler = requestMapping.getHandler(request);
		return (HandlerMethod) handler.getHandler();
	}
	
	/**
	 * 获取当前线程的 request
	 * 
	 * @return request request
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
	}
	
}
