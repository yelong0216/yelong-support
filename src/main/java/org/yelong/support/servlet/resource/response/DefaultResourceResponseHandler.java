package org.yelong.support.servlet.resource.response;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.yelong.core.resource.ScopeResourceSupplierManager;
import org.yelong.support.resource.web.WebResourceSupplier;

/**
 * 默认的资源响应处理
 * 
 * @since 2.2
 */
public class DefaultResourceResponseHandler extends AbstractResourceResponseHandler {

	private WebResourceSupplier webResourceSupplier;

	public DefaultResourceResponseHandler(ScopeResourceSupplierManager scopeResourceSupplierManager,
			WebResourceSupplier webResourceSupplier) {
		super(scopeResourceSupplierManager);
		this.webResourceSupplier = webResourceSupplier;
	}

	@Override
	protected InputStream getRequestResourceInputStream(ResourceResponseProperties resourceResponseProperties)
			throws ResourceResponseException, IOException {
		// 先判断本地是否存在相同路径的资源。如果存在则使用本地相同路径的资源而不是用依赖包中的资源。
		HttpServletRequest request = resourceResponseProperties.getRequest();
		ServletContext servletContext = request.getServletContext();
//		String resourcePath = resourceResponseProperties.getRequestResourcePath();//这个是去除掉Servlet的路径
		String resourcePath = request.getRequestURI();//这个是请求的全路径
		InputStream inputStream = webResourceSupplier.getResourceAsStream(servletContext, resourcePath);
		if (null != inputStream) {
			return inputStream;
		}
		return super.getRequestResourceInputStream(resourceResponseProperties);
	}

}
