/**
 * 
 */
package org.yelong.support.spring.mvc;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.yelong.support.spring.ApplicationContextDecorator;

/**
 * @since 1.0
 */
public final class SpringMvcUtils {

	private SpringMvcUtils() {
	}

	/**
	 * 获取请求映射的处理器方法
	 * 
	 * @param request 请求
	 * @return 处理器方法
	 * @throws Exception {@like RequestMappingHandlerMapping#getHandler(HttpServletRequest)}
	 */
	public static HandlerMethod getRequestMappingHandlerMethod(HttpServletRequest request) throws Exception {
		RequestMappingHandlerMapping requestMapping = ApplicationContextDecorator
				.getBean(RequestMappingHandlerMapping.class);
		HandlerExecutionChain handler = requestMapping.getHandler(request);
		return (HandlerMethod) handler.getHandler();
	}

	/**
	 * 获取当前请求映射的处理器方法
	 * 
	 * @param request 请求
	 * @return 处理器方法
	 * @throws Exception {@like RequestMappingHandlerMapping#getHandler(HttpServletRequest)}
	 * @since 1.3.0
	 */
	public static HandlerMethod getCurrentRequestMappingHandlerMethod() throws Exception {
		RequestMappingHandlerMapping requestMapping = ApplicationContextDecorator
				.getBean(RequestMappingHandlerMapping.class);
		HandlerExecutionChain handler = requestMapping.getHandler(getRequest());
		return (HandlerMethod) handler.getHandler();
	}

	/**
	 * 获取当前线程的 request
	 * 
	 * @return request request
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 注册Controller到Spring容器中
	 * 
	 * @param beanName bean name
	 * @param beanClass bean class
	 * @param applicationContext application context
	 * @throws Exception registry exception
	 */
	public static void registryController(String beanName, Class<?> beanClass, ApplicationContext applicationContext)
			throws Exception {
		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext
				.getAutowireCapableBeanFactory();
		// 这里通过builder直接生成了mycontrooler的definition，然后注册进去
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
		defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
		RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext
				.getBean(RequestMappingHandlerMapping.class);
		Method method = requestMappingHandlerMapping.getClass().getSuperclass().getSuperclass()
				.getDeclaredMethod("detectHandlerMethods", Object.class);
		method.setAccessible(true);
		method.invoke(requestMappingHandlerMapping, beanName);
	}

}
