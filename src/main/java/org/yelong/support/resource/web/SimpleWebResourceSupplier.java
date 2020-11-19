package org.yelong.support.resource.web;

import java.io.InputStream;

import javax.servlet.ServletContext;

/**
 * 简单的WEB资源供应商
 * 
 * @since 2.2
 */
public final class SimpleWebResourceSupplier implements WebResourceSupplier {
	
	public static final WebResourceSupplier INSTANCE = new SimpleWebResourceSupplier();
	
	private SimpleWebResourceSupplier() {
	}

	@Override
	public InputStream getResourceAsStream(ServletContext servletContext, String resourcePath) {
		return servletContext.getResourceAsStream(resourcePath);
	}

}
