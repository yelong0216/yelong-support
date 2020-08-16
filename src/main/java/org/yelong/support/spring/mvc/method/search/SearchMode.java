/**
 * 
 */
package org.yelong.support.spring.mvc.method.search;

/**
 * 检索模式
 * 
 * @since 2.0
 */
public enum SearchMode {

	/**前缀*/
	PREFIX,
	/**等于*/
	EQ,
	/**包含*/
	CONTAINS,
	/**后缀*/
	SUFFIX;
}
