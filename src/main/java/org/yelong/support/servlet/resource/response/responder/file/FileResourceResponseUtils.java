package org.yelong.support.servlet.resource.response.responder.file;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.yelong.commons.io.IOUtilsE;
import org.yelong.support.servlet.resource.response.ResourceResponseException;
import org.yelong.support.servlet.resource.response.ResourceResponseProperties;

/**
 * 文件资源响应工具类
 * 
 * @since 2.2
 */
public final class FileResourceResponseUtils {

	private FileResourceResponseUtils() {
	}

	/**
	 * 响应文本
	 * 
	 * @param resourceInputStream        资源流
	 * @param resourceResponseProperties 资源响应配置
	 */
	public static void responseText(InputStream resourceInputStream,
			ResourceResponseProperties resourceResponseProperties) throws IOException, ResourceResponseException {
		String text = IOUtilsE.readString(resourceInputStream);
		IOUtilsE.closeQuietly(resourceInputStream);
		responseText(text, resourceResponseProperties);
	}

	/**
	 * 响应文本
	 * 
	 * @param textSupplier               响应文件提供者
	 * @param resourceResponseProperties 资源响应配置
	 */
	public static void responseText(ResourceSupplier<String> textSupplier,
			ResourceResponseProperties resourceResponseProperties) throws IOException, ResourceResponseException {
		responseText(textSupplier.get(), resourceResponseProperties);
	}

	/**
	 * 响应文本
	 * 
	 * @param textSupplier               响应文件提供者
	 * @param resourceResponseProperties 资源响应配置
	 */
	public static void responseText(String text, ResourceResponseProperties resourceResponseProperties)
			throws IOException, ResourceResponseException {
		HttpServletResponse response = resourceResponseProperties.getResponse();
		response.getWriter().write(text);
	}

	/**
	 * 响应二进制流
	 * 
	 * @param resourceInputStream        资源流
	 * @param resourceResponseProperties 资源响应配置
	 */
	public static void responseBytes(InputStream resourceInputStream,
			ResourceResponseProperties resourceResponseProperties) throws IOException, ResourceResponseException {
		HttpServletResponse response = resourceResponseProperties.getResponse();
		byte[] bytes = IOUtils.toByteArray(resourceInputStream);
		IOUtilsE.closeQuietly(resourceInputStream);
		response.getOutputStream().write(bytes);
	}

	/**
	 * 响应文本。支持304状态码
	 * 
	 * @param resourceInputStream        资源流
	 * @param resourceResponseProperties 资源响应配置
	 */
	public static void responseTextSupport304(InputStream resourceInputStream,
			ResourceResponseProperties resourceResponseProperties) throws IOException, ResourceResponseException {
		HttpServletRequest request = resourceResponseProperties.getRequest();
		HttpServletResponse response = resourceResponseProperties.getResponse();
		if (Status304.handle(request, response)) {
			return;
		}
		String text = IOUtilsE.readString(resourceInputStream);
		IOUtilsE.closeQuietly(resourceInputStream);
		response.getWriter().write(text);
	}

	/**
	 * 响应文本。支持304状态码
	 * 
	 * @param text                       响应文件
	 * @param resourceResponseProperties 资源响应配置
	 */
	public static void responseTextSupport304(String text, ResourceResponseProperties resourceResponseProperties)
			throws IOException, ResourceResponseException {
		HttpServletRequest request = resourceResponseProperties.getRequest();
		HttpServletResponse response = resourceResponseProperties.getResponse();
		if (Status304.handle(request, response)) {
			return;
		}
		response.getWriter().write(text);
	}

	/**
	 * 响应文本。支持304状态码
	 * 
	 * @param textSupplier               响应文件提供者
	 * @param resourceResponseProperties 资源响应配置
	 */
	public static void responseTextSupport304(ResourceSupplier<String> textSupplier,
			ResourceResponseProperties resourceResponseProperties) throws IOException, ResourceResponseException {
		HttpServletRequest request = resourceResponseProperties.getRequest();
		HttpServletResponse response = resourceResponseProperties.getResponse();
		if (Status304.handle(request, response)) {
			return;
		}
		response.getWriter().write(textSupplier.get());
	}

	/**
	 * 响应二进制流。支持304状态码
	 * 
	 * @param resourceInputStream        资源流
	 * @param resourceResponseProperties 资源响应配置
	 */
	public static void responseBytesSupport304(InputStream resourceInputStream,
			ResourceResponseProperties resourceResponseProperties) throws IOException, ResourceResponseException {
		HttpServletRequest request = resourceResponseProperties.getRequest();
		HttpServletResponse response = resourceResponseProperties.getResponse();
		if (Status304.handle(request, response)) {
			return;
		}
		byte[] bytes = IOUtils.toByteArray(resourceInputStream);
		IOUtilsE.closeQuietly(resourceInputStream);
		response.getOutputStream().write(bytes);
	}

	/**
	 * 响应二进制流。支持304状态码
	 * 
	 * @param resourceInputStream        资源流
	 * @param resourceResponseProperties 资源响应配置
	 */
	public static void responseBytesSupport304(ResourceSupplier<InputStream> resourceInputStreamSupplier,
			ResourceResponseProperties resourceResponseProperties) throws IOException, ResourceResponseException {
		HttpServletRequest request = resourceResponseProperties.getRequest();
		HttpServletResponse response = resourceResponseProperties.getResponse();
		if (Status304.handle(request, response)) {
			return;
		}
		InputStream inputStream = resourceInputStreamSupplier.get();
		byte[] bytes = IOUtils.toByteArray(inputStream);
		IOUtilsE.closeQuietly(inputStream);
		response.getOutputStream().write(bytes);
	}

	/**
	 * 资源供应商
	 *
	 * @param <T> 资源类型
	 */
	public static interface ResourceSupplier<T> {

		/**
		 * 获取资源
		 * 
		 * @return 资源
		 */
		T get() throws IOException, ResourceResponseException;

	}

}
