/**
 * 
 */
package org.yelong.support.spring.boot.servlet.resource;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.yelong.commons.lang.StringUtilsE;
import org.yelong.core.resource.ScopeResourceSupplier;
import org.yelong.core.resource.ScopeResourceSupplierFactory;
import org.yelong.support.servlet.resource.ResourceServlet;
import org.yelong.support.servlet.resource.response.ResourceResponseHandler;

/**
 * 资源Servlet注册Bean
 * 
 * @since 2.2
 */
public class ResourceServletRegistrationBean<T extends ResourceServlet> extends ServletRegistrationBean<T>
		implements InitializingBean {

	private String urlPrefix;

	private String resourceRootPath;

	private T resourceServlet;

	@Resource
	private ScopeResourceSupplierFactory scopeResourceSupplierFactory;

	@Resource
	private ResourceResponseHandler resourceResponseHandler;

	public ResourceServletRegistrationBean() {
	}

	/**
	 * @param urlPrefix        URL前缀
	 * @param resourceRootPath 资源根路径
	 * @param resourceServlet  资源Servlet
	 */
	public ResourceServletRegistrationBean(String urlPrefix, String resourceRootPath, T resourceServlet) {
		this.urlPrefix = urlPrefix;
		this.resourceRootPath = resourceRootPath;
		this.resourceServlet = resourceServlet;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ScopeResourceSupplier scopeResourceSupplier = scopeResourceSupplierFactory.create(urlPrefix, resourceRootPath);
		resourceResponseHandler.getScopeResourceSupplierManager().registerScopeResourceSupplier(scopeResourceSupplier);
		resourceServlet.setResourceResponseHandler(resourceResponseHandler);
		setServlet(resourceServlet);
		String urlMapping = this.urlPrefix;
		urlMapping = StringUtilsE.appendStartsSlash(urlMapping);
		urlMapping = StringUtilsE.appendEndsSlash(urlMapping);
		urlMapping = urlMapping + "*";
		addUrlMappings(urlMapping);
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

	public String getResourceRootPath() {
		return resourceRootPath;
	}

	public void setResourceRootPath(String resourceRootPath) {
		this.resourceRootPath = resourceRootPath;
	}

	public T getResourceServlet() {
		return resourceServlet;
	}

	public void setResourceServlet(T resourceServlet) {
		this.resourceServlet = resourceServlet;
	}

	public ScopeResourceSupplierFactory getScopeResourceSupplierFactory() {
		return scopeResourceSupplierFactory;
	}

	public void setScopeResourceSupplierFactory(ScopeResourceSupplierFactory scopeResourceSupplierFactory) {
		this.scopeResourceSupplierFactory = scopeResourceSupplierFactory;
	}

	public ResourceResponseHandler getResourceResponseHandler() {
		return resourceResponseHandler;
	}

	public void setResourceResponseHandler(ResourceResponseHandler resourceResponseHandler) {
		this.resourceResponseHandler = resourceResponseHandler;
	}

}
