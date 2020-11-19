package org.yelong.support.resource.web;

import java.io.InputStream;
import java.util.Objects;

import javax.servlet.ServletContext;

import org.yelong.commons.lang.StringUtilsE;
import org.yelong.core.annotation.Nullable;
import org.yelong.core.resource.AbstractScopeResourceSupplier;

/**
 * 简单的WEB范围资源供应商
 * 
 * @since 2.2
 */
public class SimpleWebScopeResourceSupplier extends AbstractScopeResourceSupplier implements WebScopeResourceSupplier {

	public SimpleWebScopeResourceSupplier(@Nullable String urlMapping, @Nullable String resourceScopePath) {
		super(urlMapping, resourceScopePath);
	}

	@Override
	public InputStream getResourceAsStream(ServletContext servletContext, String resourcePath, boolean urlMapping) {
		Objects.requireNonNull(resourcePath);
		if (urlMapping && this.urlMapping != null) {
			if (resourcePath.startsWith(this.urlMapping)) {
				resourcePath = resourcePath.substring(this.urlMapping.length());
			}
		}
		String resourceScopePath = StringUtilsE.deleteEndsSlash(this.resourceScopePath);
		resourcePath = StringUtilsE.appendStartsSlash(resourcePath);
		return servletContext.getResourceAsStream(resourceScopePath + resourcePath);
	}

}
