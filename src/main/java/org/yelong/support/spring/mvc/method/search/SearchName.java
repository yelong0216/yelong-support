/**
 * 
 */
package org.yelong.support.spring.mvc.method.search;

import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

/**
 * 检索名称
 * 
 * @since 2.0
 */
public enum SearchName {

	/**
	 * @see RequestMappingInfo#getName()
	 */
	NAME,
	/**
	 * @see RequestMappingInfo#getPatternsCondition()
	 */
	PATTERN,
	/**
	 * @see RequestMappingInfo#getMethodsCondition()
	 */
	METHOD;

}
