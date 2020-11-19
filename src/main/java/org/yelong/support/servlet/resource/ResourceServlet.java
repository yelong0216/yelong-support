package org.yelong.support.servlet.resource;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.yelong.commons.lang.CharsetUtilsE;
import org.yelong.support.servlet.resource.response.ResourceResponseHandler;
import org.yelong.support.servlet.resource.response.ResourceResponseProperties;

/**
 * 资源控制器
 * 
 * Servlet 同一个类注册多个对象时只有第一个会起作用，对于这种情况，在注入多个时通过拓展该类来实现
 * 
 * @since 2.2
 */
public abstract class ResourceServlet extends HttpServlet {

	private static final long serialVersionUID = -8047369128985763547L;

	protected ResourceServletProperties resourceServletProperties;

	protected ResourceResponseHandler resourceResponseHandler;

	protected ResourceServlet() {
	}

	protected ResourceServlet(ResourceResponseHandler resourceResponseHandler) {
		this(null, resourceResponseHandler);
	}

	protected ResourceServlet(ResourceServletProperties resourceServletProperties,
			ResourceResponseHandler resourceResponseHandler) {
		this.resourceResponseHandler = resourceResponseHandler;
		this.resourceServletProperties = resourceServletProperties;
	}

	@Override
	public void init() throws ServletException {
		Objects.requireNonNull(resourceResponseHandler);
		if (null == resourceServletProperties) {
			resourceServletProperties = new ResourceServletProperties();
		}
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String encoding = resourceServletProperties.getEncoding();
		if (StringUtils.isBlank(encoding)) {
			encoding = CharsetUtilsE.UTF_8;
		}
		response.setCharacterEncoding(encoding);
		ResourceResponseProperties resourceResponseProperties = new ResourceResponseProperties(request, response);
		resourceResponseHandler.handle(resourceResponseProperties);
	}

	public ResourceServletProperties getResourceServletProperties() {
		return resourceServletProperties;
	}

	public ResourceResponseHandler getResourceResponseHandler() {
		return resourceResponseHandler;
	}

	public void setResourceResponseHandler(ResourceResponseHandler resourceResponseHandler) {
		this.resourceResponseHandler = resourceResponseHandler;
	}

	public void setResourceServletProperties(ResourceServletProperties resourceServletProperties) {
		this.resourceServletProperties = resourceServletProperties;
	}

}
