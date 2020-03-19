/**
 * 
 */
package org.yelong.support.spring;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

/**
 * ApplicationContext装饰器
 * 如果这个类没有被Spring进行初始化则使用会出现空指针异常<br/>
 * 这个类不应该在属于Spring容器的对象中使用（导致该类未初始化的错误）
 * @author 彭飞
 * @date 2019年7月9日上午8:46:19
 */
public class ApplicationContextDecorator implements ApplicationContextAware{

	private static ApplicationContext APPLICATION_CONTEXT;
	//private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextDecorator.class);
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if( null == applicationContext ) {
			throw new NullPointerException();
		}
		APPLICATION_CONTEXT = applicationContext;
		//LOGGER.info("SpringContextDecorator初始化成功！");
	}

	
	public static Environment getEnvironment() {
		return APPLICATION_CONTEXT.getEnvironment();
	}

	
	public static boolean containsBeanDefinition(String beanName) {
		return APPLICATION_CONTEXT.containsBeanDefinition(beanName);
	}

	
	public static int getBeanDefinitionCount() {
		return APPLICATION_CONTEXT.getBeanDefinitionCount();
	}

	
	public static String[] getBeanDefinitionNames() {
		return APPLICATION_CONTEXT.getBeanDefinitionNames();
	}

	
	public static String[] getBeanNamesForType(Class<?> type) {
		return APPLICATION_CONTEXT.getBeanNamesForType(type);
	}

	
	public static String[] getBeanNamesForType(Class<?> type, boolean includeNonSingletons, boolean allowEagerInit) {
		return APPLICATION_CONTEXT.getBeanNamesForType(type, includeNonSingletons, allowEagerInit);
	}

	
	public static <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
		return APPLICATION_CONTEXT.getBeansOfType(type);
	}

	
	public static <T> Map<String, T> getBeansOfType(Class<T> type, boolean includeNonSingletons, boolean allowEagerInit)
			throws BeansException {
		return APPLICATION_CONTEXT.getBeansOfType(type, includeNonSingletons, allowEagerInit);
	}

	
	public static String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType) {
		return APPLICATION_CONTEXT.getBeanNamesForAnnotation(annotationType);
	}

	
	public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType)
			throws BeansException {
		return APPLICATION_CONTEXT.getBeansWithAnnotation(annotationType);
	}

	
	public static <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType)
			throws NoSuchBeanDefinitionException {
		return APPLICATION_CONTEXT.findAnnotationOnBean(beanName, annotationType);
	}

	
	public static Object getBean(String name) throws BeansException {
		return APPLICATION_CONTEXT.getBean(name);
	}

	
	public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return APPLICATION_CONTEXT.getBean(name, requiredType);
	}

	
	public static <T> T getBean(Class<T> requiredType) throws BeansException {
		return APPLICATION_CONTEXT.getBean(requiredType);
	}

	
	public static Object getBean(String name, Object... args) throws BeansException {
		return APPLICATION_CONTEXT.getBean(name, args);
	}

	
	public static boolean containsBean(String name) {
		return APPLICATION_CONTEXT.containsBean(name);
	}

	
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return APPLICATION_CONTEXT.isSingleton(name);
	}

	
	public static boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
		return APPLICATION_CONTEXT.isPrototype(name);
	}

	
	public static boolean isTypeMatch(String name, Class<?> targetType) throws NoSuchBeanDefinitionException {
		return APPLICATION_CONTEXT.isTypeMatch(name, targetType);
	}

	
	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
		return APPLICATION_CONTEXT.getType(name);
	}

	
	public static String[] getAliases(String name) {
		return APPLICATION_CONTEXT.getAliases(name);
	}

	
	public static BeanFactory getParentBeanFactory() {
		return APPLICATION_CONTEXT.getParentBeanFactory();
	}

	
	public static boolean containsLocalBean(String name) {
		return APPLICATION_CONTEXT.containsLocalBean(name);
	}

	
	public static String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		return APPLICATION_CONTEXT.getMessage(code, args, defaultMessage, locale);
	}

	
	public static String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
		return APPLICATION_CONTEXT.getMessage(code, args, locale);
	}

	
	public static String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
		return APPLICATION_CONTEXT.getMessage(resolvable, locale);
	}

	
	public static void publishEvent(ApplicationEvent event) {
		APPLICATION_CONTEXT.publishEvent(event);
		
	}

	
	public static Resource[] getResources(String locationPattern) throws IOException {
		return APPLICATION_CONTEXT.getResources(locationPattern);
	}

	
	public static Resource getResource(String location) {
		return APPLICATION_CONTEXT.getResource(location);
	}

	
	public static ClassLoader getClassLoader() {
		return APPLICATION_CONTEXT.getClassLoader();
	}

	
	public static String getId() {
		return APPLICATION_CONTEXT.getId();
	}

	
	public static String getApplicationName() {
		return APPLICATION_CONTEXT.getApplicationName();
	}

	
	public static String getDisplayName() {
		return APPLICATION_CONTEXT.getDisplayName();
	}

	
	public static long getStartupDate() {
		return APPLICATION_CONTEXT.getStartupDate();
	}

	
	public static ApplicationContext getParent() {
		return APPLICATION_CONTEXT.getParent();
	}

	
	public static AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException {
		return APPLICATION_CONTEXT.getAutowireCapableBeanFactory();
	}

}
