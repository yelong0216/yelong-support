/**
 * 
 */
package org.yelong.support.spring.mvc.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.yelong.commons.annotation.AnnotationUtils;

/**
 * 抽象mvc拦截器。 提供一些拦截器常用的方法
 * 
 * 可以在拦截器中抛出异常，由异常处理器来处理这些异常
 * 
 * @author PengFei
 */
public abstract class AbstractHandlerInterceptor extends HandlerInterceptorAdapter {

	protected static final String DEFAULT_RESPONSE_CONTENT_TYPE = "text/html;charset=UTF-8";

	/**
	 * 使用默认的contentType响应信息<br/>
	 * {@link #DEFAULT_RESPONSE_CONTENT_TYPE}
	 * 
	 * @param response http响应对象
	 * @param message  响应消息
	 * @throws IOException 流异常
	 */
	@Deprecated
	protected void writer(HttpServletResponse response, String message) throws IOException {
		writer(response, message, DEFAULT_RESPONSE_CONTENT_TYPE);
	}

	/**
	 * 使用指定的contentType响应数据<br/>
	 * 
	 * @param response    http响应对象
	 * @param message     响应消息
	 * @param contentType 文本类型
	 * @throws IOException 流异常
	 */
	@Deprecated
	protected void writer(HttpServletResponse response, String message, String contentType) throws IOException {
		response.setContentType(contentType);
		PrintWriter out = response.getWriter();
		out.write(message);
	}

	/**
	 * 获取handler方法上面的的注解。如果方法上面没有到，则根据该方法所属的类层级递归查找
	 * 
	 * @param <A>        annotation type
	 * @param handler    处理器
	 * @param annotation 注解
	 * @return annotation
	 * @since 1.0.5
	 */
	protected <A extends Annotation> A getHandlerMethodAnnotation(HandlerMethod handler, Class<A> annotation) {
		return getHandlerMethodAnnotation(handler, annotation, true);
	}

	/**
	 * 获取handler方法上面的的注解。
	 * 
	 * @param <A>            annotation type
	 * @param handler        处理器
	 * @param annotation     注解
	 * @param classRecursive 类递归。<tt>true</tt> 如果方法上面没有到，则根据该方法所属的类层级递归查找
	 * @return annotation
	 * @since 1.0.5
	 */
	protected <A extends Annotation> A getHandlerMethodAnnotation(HandlerMethod handler, Class<A> annotation,
			boolean classRecursive) {
		Method method = handler.getMethod();
		if (method.isAnnotationPresent(annotation)) {
			return method.getAnnotation(annotation);
		}
		if (!classRecursive) {
			return null;
		}
		Class<?> c = handler.getBeanType();
		return AnnotationUtils.getAnnotation(c, annotation, true);
	}

}
