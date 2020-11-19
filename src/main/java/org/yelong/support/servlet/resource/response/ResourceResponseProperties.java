package org.yelong.support.servlet.resource.response;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 资源响应配置属性
 * 
 * @since 2.2
 */
public class ResourceResponseProperties {

	protected final HttpServletRequest request;

	protected final HttpServletResponse response;

	private String requestResourcePath;

	public ResourceResponseProperties(HttpServletRequest request, HttpServletResponse response) {
		this.request = Objects.requireNonNull(request);
		this.response = Objects.requireNonNull(response);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @return 请求的资源路径
	 */
	public String getRequestResourcePath() {
		if (null != requestResourcePath) {
			return requestResourcePath;
		}
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		String requestURI = request.getRequestURI();
		requestResourcePath = requestURI.substring(contextPath.length() + servletPath.length());
		return requestResourcePath;
	}

}
