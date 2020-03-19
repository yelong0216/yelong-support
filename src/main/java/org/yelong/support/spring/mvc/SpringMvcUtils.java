/**
 * 
 */
package org.yelong.support.spring.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.yelong.support.spring.ApplicationContextDecorator;

/**
 * @author PengFei
 */
public class SpringMvcUtils {

	/**
	 * 获取请求映射的处理器方法
	 * @param request
	 * @return 处理器方法
	 * @throws Exception
	 */
	public static HandlerMethod getRequestMappingHandlerMethod(HttpServletRequest request) throws Exception {
		RequestMappingHandlerMapping requestMapping = ApplicationContextDecorator.getBean(RequestMappingHandlerMapping.class);
		HandlerExecutionChain handler = requestMapping.getHandler(request);
		return (HandlerMethod) handler.getHandler();
	}
	
}
