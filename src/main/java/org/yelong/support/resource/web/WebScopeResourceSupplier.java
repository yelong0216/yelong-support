/**
 * 
 */
package org.yelong.support.resource.web;

import java.io.InputStream;
import java.util.Objects;

import javax.servlet.ServletContext;

import org.yelong.core.annotation.Nullable;
import org.yelong.core.resource.ScopeResourceSupplier;

/**
 * web范围资源供应商
 * 
 * @since 2.2
 */
public interface WebScopeResourceSupplier extends ScopeResourceSupplier, WebResourceSupplier {

	ThreadLocal<ServletContext> servletContextThreadLocal = new ThreadLocal<ServletContext>();

	/**
	 * 指定下一次获取资源时的 {@link ServletContext}
	 * 
	 * @param servletContext servletContext
	 */
	default void designateNextGetResourceStreamServletContext(ServletContext servletContext) {
		servletContextThreadLocal.set(servletContext);
	}

	@Override
	default InputStream getResourceAsStream(String resourcePath) {
		return ScopeResourceSupplier.super.getResourceAsStream(resourcePath);
	}

	@Override
	default InputStream getResourceAsStream(String resourcePath, boolean urlMapping) {
		ServletContext servletContext = servletContextThreadLocal.get();
		Objects.requireNonNull(servletContext, "没有指定 ServletContext 请先调用 designateNextGetResourceStreamServletContext");
		return getResourceAsStream(servletContext, resourcePath, urlMapping);
	}

	/**
	 * 从Web中获取资源流。
	 * 
	 * @param servletContext servletContext
	 * @param resourcePath   资源路径
	 * @return 资源流
	 * @see {@link #getResourceAsStream(ServletContext, String, boolean)}
	 */
	@Nullable
	default InputStream getResourceAsStream(ServletContext servletContext, String resourcePath) {
		return getResourceAsStream(servletContext, resourcePath, false);
	}

	/**
	 * 从Web中获取资源流。
	 * 
	 * @param servletContext servletContext
	 * @param resourcePath   资源路径
	 * @param urlMapping     是否启用URL映射
	 * @return 资源流
	 * @see {@link #getResourceAsStream(String, boolean)}
	 */
	@Nullable
	InputStream getResourceAsStream(ServletContext servletContext, String resourcePath, boolean urlMapping);

}
