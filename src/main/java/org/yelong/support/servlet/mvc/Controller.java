/**
 * 
 */
package org.yelong.support.servlet.mvc;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yelong.core.annotation.Nullable;
import org.yelong.support.servlet.ServletRequests;

/**
 * 控制器
 * 
 * @since 1.0
 */
public interface Controller {

	/**
	 * @return HttpServletRequest
	 */
	HttpServletRequest getRequest();

	/**
	 * @return HttpServletResponse
	 */
	HttpServletResponse getResponse();

	/**
	 * 响应一个文本
	 * 
	 * @param text 内容
	 */
	void responseText(String text) throws IOException;

	/**
	 * 响应一个文件
	 * 
	 * @param file 文件
	 */
	void responseFile(File file) throws IOException;

	/**
	 * 响应一个文件并指定文件的名称
	 * 
	 * @param file     文件
	 * @param filename 响应的文件名称。默认为 {@link File#getName()}
	 * @throws IOException
	 */
	void responseFile(File file, @Nullable String filename) throws IOException;

	/**
	 * 响应文件
	 * 
	 * @param fileByteArray 文件流
	 * @param fileName      文件名称
	 * @throws IOException
	 */
	void responseFile(String fileName, byte[] fileBytsArray) throws IOException;

	/**
	 * 设置响应内容
	 * 
	 * @param content 内容字节数组
	 */
	void responseContent(byte[] content) throws IOException;

	// ==================================================getParameter==================================================

	/**
	 * 获取参数值
	 * 
	 * @param name 参数名称
	 * @return 参数值
	 */
	default String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	/**
	 * 获取参数，如果参数不存在则返回默认值
	 * 
	 * @param name         参数名称
	 * @param defaultValue 默认值
	 * @return 参数值，如果不存在则返回默认值
	 */
	default String getParameter(String name, String defaultValue) {
		return ServletRequests.getParameter(getRequest(), name, defaultValue);
	}

	/**
	 * 获取参数的值并转换为{@link Integer}
	 * 
	 * @param name    参数名称
	 * @return 参数值，如果不存在则返回null，否则转换为 {@link Integer}
	 * @see Integer#valueOf(String)
	 */
	default Integer getParameterInteger(String name) {
		return ServletRequests.getParameterInteger(getRequest(), name);
	}

	/**
	 * 获取参数值并转换为{@link Integer}，如果值不存在则返回默认值
	 * 
	 * @param name         参数名称
	 * @param defaultValue 默认值
	 * @return 参数值，如果不存在则返回默认值，否则转换为 {@link Integer}
	 */
	default Integer getParameterInteger(String name, Integer defaultValue) {
		return ServletRequests.getParameterInteger(getRequest(), name, defaultValue);
	}

	/**
	 * 获取参数值并转换为 {@link Long}，如果值不存在则返回 <code>null</code>
	 * 
	 * @param name    参数名称
	 * @return 参数值，如果不存在则返回 <code>null</code>， 否则转换为 {@link Long}
	 */
	default Long getParameterLong(String name) {
		return ServletRequests.getParameterLong(getRequest(), name);
	}

	/**
	 * 获取参数值并转换为{@link Long}，如果值不存在则返回默认值
	 * 
	 * @param name         参数名称
	 * @param defaultValue 默认值
	 * @return 参数值，如果不存在则返回默认值，否则转换为 {@link Long}
	 */
	default Long getParameterLong(String name, Long defaultValue) {
		return ServletRequests.getParameterLong(getRequest(), name, defaultValue);
	}

	/**
	 * 获取参数值并转换为 {@link Boolean}，如果值不存在则返回 <code>null</code>
	 * 
	 * @param name    参数名称
	 * @return 参数值，如果不存在则返回 <code>null</code>， 否则转换为 {@link Boolean}
	 */
	default Boolean getParameterBoolean(String name) {
		return ServletRequests.getParameterBoolean(getRequest(), name);
	}

	/**
	 * 获取参数值并转换为{@link Boolean}，如果值不存在则返回默认值
	 * 
	 * @param name         参数名称
	 * @param defaultValue 默认值
	 * @return 参数值，如果不存在则返回默认值，否则转换为 {@link Boolean}
	 */
	default Boolean getParameterBoolean(String name, Boolean defaultValue) {
		return ServletRequests.getParameterBoolean(getRequest(), name, defaultValue);
	}

}
