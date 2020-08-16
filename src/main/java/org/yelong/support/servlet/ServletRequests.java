/**
 * 
 */
package org.yelong.support.servlet;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * {@link ServletRequest}工具类
 * 
 * @since 2.0
 */
public final class ServletRequests {

	private ServletRequests() {
	}

	/**
	 * 获取参数，如果参数不存在则返回默认值
	 * 
	 * @param request      请求
	 * @param name         参数名称
	 * @param defaultValue 默认值
	 * @return 参数值，如果不存在则返回默认值
	 */
	public static String getParameter(ServletRequest request, String name, String defaultValue) {
		String value = request.getParameter(name);
		return isNotEmpty(value) ? value : defaultValue;
	}

	/**
	 * 获取参数的值并转换为{@link Integer}
	 * 
	 * @param request 请求
	 * @param name    参数名称
	 * @return 参数值，如果不存在则返回null，否则转换为 {@link Integer}
	 * @see Integer#valueOf(String)
	 */
	public static Integer getParameterInteger(ServletRequest request, String name) {
		String value = request.getParameter(name);
		return isEmpty(value) ? null : Integer.valueOf(value);
	}

	/**
	 * 获取参数值并转换为{@link Integer}，如果值不存在则返回默认值
	 * 
	 * @param request      请求
	 * @param name         参数名称
	 * @param defaultValue 默认值
	 * @return 参数值，如果不存在则返回默认值，否则转换为 {@link Integer}
	 */
	public static Integer getParameterInteger(ServletRequest request, String name, Integer defaultValue) {
		Integer value = getParameterInteger(request, name);
		return null != value ? value : defaultValue;
	}

	/**
	 * 获取参数值并转换为 {@link Long}，如果值不存在则返回 <code>null</code>
	 * 
	 * @param request 请求
	 * @param name    参数名称
	 * @return 参数值，如果不存在则返回 <code>null</code>， 否则转换为 {@link Long}
	 */
	public static Long getParameterLong(ServletRequest request, String name) {
		String value = request.getParameter(name);
		return isEmpty(value) ? null : Long.valueOf(value);
	}

	/**
	 * 获取参数值并转换为{@link Long}，如果值不存在则返回默认值
	 * 
	 * @param request      请求
	 * @param name         参数名称
	 * @param defaultValue 默认值
	 * @return 参数值，如果不存在则返回默认值，否则转换为 {@link Long}
	 */
	public static Long getParameterLong(ServletRequest request, String name, Long defaultValue) {
		Long value = getParameterLong(request, name);
		return null != value ? value : defaultValue;
	}

	/**
	 * 获取参数值并转换为 {@link Boolean}，如果值不存在则返回 <code>null</code>
	 * 
	 * @param request 请求
	 * @param name    参数名称
	 * @return 参数值，如果不存在则返回 <code>null</code>， 否则转换为 {@link Boolean}
	 */
	public static Boolean getParameterBoolean(ServletRequest request, String name) {
		String value = request.getParameter(name);
		return isEmpty(value) ? null : Boolean.valueOf(value);
	}

	/**
	 * 获取参数值并转换为{@link Boolean}，如果值不存在则返回默认值
	 * 
	 * @param request      请求
	 * @param name         参数名称
	 * @param defaultValue 默认值
	 * @return 参数值，如果不存在则返回默认值，否则转换为 {@link Boolean}
	 */
	public static Boolean getParameterBoolean(ServletRequest request, String name, Boolean defaultValue) {
		Boolean value = getParameterBoolean(request, name);
		return null != value ? value : defaultValue;
	}

	private static boolean isEmpty(String value) {
		return StringUtils.isEmpty(value);
	}

	private static boolean isNotEmpty(String value) {
		return StringUtils.isNotEmpty(value);
	}

}
