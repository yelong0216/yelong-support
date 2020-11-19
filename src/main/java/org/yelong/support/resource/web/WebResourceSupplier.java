/**
 * 
 */
package org.yelong.support.resource.web;

import java.io.InputStream;
import java.util.Objects;

import javax.servlet.ServletContext;

import org.yelong.core.annotation.Nullable;
import org.yelong.core.resource.ResourceSupplier;

/**
 * web范围资源供应商
 * 
 * @since 2.2
 */
public interface WebResourceSupplier extends ResourceSupplier {

	ThreadLocal<ServletContext> servletContextThreadLocal = new ThreadLocal<ServletContext>();

	/**
	 * 指定下一次获取资源时的 {@link ServletContext}
	 * 
	 * @param servletContext servletContext
	 */
	default void designateNextGetResourceStreamServletContext(ServletContext servletContext) {
		servletContextThreadLocal.set(servletContext);
	}

	/**
	 * 从Web中获取资源流。
	 * 
	 * @param servletContext servletContext
	 * @param resourcePath   资源路径
	 * @return 资源流
	 * @see {@link #getResourceAsStream(ServletContext, String, boolean)}
	 */
	@Override
	default InputStream getResourceAsStream(String resourcePath) {
		ServletContext servletContext = servletContextThreadLocal.get();
		Objects.requireNonNull(servletContext, "没有指定 ServletContext 请先调用 designateNextGetResourceStreamServletContext");
		return getResourceAsStream(servletContext, resourcePath);
	}

	/**
	 * 从Web中获取资源流。
	 * 
	 * @param servletContext servletContext
	 * @param resourcePath   资源路径
	 * @return 资源流
	 */
	@Nullable
	InputStream getResourceAsStream(ServletContext servletContext, String resourcePath);

}
