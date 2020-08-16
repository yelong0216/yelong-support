package org.yelong.support.spring.mvc.method.search;

import java.util.List;
import java.util.Map;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.yelong.core.annotation.Nullable;

/**
 * 请求与处理方法检索器
 * 
 * @since 2.0
 * @see RequestMappingHandlerMapping#getHandlerMethods()
 */
public interface RequestMappingHandlerMethodSearcher {

	/**
	 * 根据条件检索匹配的请求映射信息
	 * 
	 * @param searchCondition 检索条件
	 * @return 匹配的请求映射信息
	 */
	Map<RequestMappingInfo, HandlerMethod> search(@Nullable SearchCondition searchCondition);

	/**
	 * 根据条件检索匹配的请求条件信息
	 * 
	 * @param searchConditions 检索条件集合
	 * @return 匹配的请求映射信息
	 */
	Map<RequestMappingInfo, HandlerMethod> search(@Nullable List<SearchCondition> searchConditions);

	/**
	 * 请求映射信息是否与条件匹配
	 * 
	 * @param requestMappingInfo 请求映射信息
	 * @param handlerMethod      处理方法
	 * @param searchCondition    检索条件
	 * @return <code>true</code> 与条件匹配
	 */
	boolean matches(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod,
			SearchCondition searchCondition);

	/**
	 * 请求映射信息是否与一组条件都匹配
	 * 
	 * @param requestMappingInfo 请求映射信息
	 * @param handlerMethod      处理方法
	 * @param searchConditions   检索条件集合
	 * @return <code>true</code> 与所有条件都匹配
	 */
	boolean matches(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod,
			List<SearchCondition> searchConditions);

	/**
	 * @return RequestMappingHandlerMapping
	 */
	RequestMappingHandlerMapping getRequestMappingHandlerMapping();

}
