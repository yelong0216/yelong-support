package org.yelong.support.servlet.resource.response.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.yelong.commons.lang.StringUtilsE;
import org.yelong.core.resource.ResourceSupplier;
import org.yelong.support.resource.web.WebResourceSupplier;
import org.yelong.support.servlet.resource.response.ResourceResponseException;
import org.yelong.support.servlet.resource.response.ResourceResponseProperties;
import org.yelong.support.servlet.resource.response.responder.file.impl.html.HTMLFileResourceResponder;

/**
 * 默认实现
 * 
 * @since 2.2
 */
public class DefaultResourceResponseSupporter implements ResourceResponseSupporter {

	@Resource
	private ResourceSupplier resourceSupplier;

	@Resource
	private WebResourceSupplier webResourceSupplier;

	@Resource
	private HTMLFileResourceResponder htmlFileResourceResponder;

	@Override
	public void responseHtml(ResourceResponseProperties resourceResponseProperties, String resourcePackagePath,
			String resourceRelativePath) throws ResourceResponseException, IOException {
		resourceRelativePath = StringUtilsE.appendStartsSlash(resourceRelativePath);
		HttpServletRequest request = resourceResponseProperties.getRequest();
		InputStream inputStream = webResourceSupplier.getResourceAsStream(request.getServletContext(),
				WEB_INF + resourceRelativePath);
		if (null == inputStream) {
			String resourcePath = resourcePackagePath.replaceAll("[.]", "/");
			resourcePath = StringUtilsE.deleteEndsSlash(resourcePath);
			resourcePath += resourceRelativePath;
			inputStream = resourceSupplier.getResourceAsStream(resourcePath);
		}
		Objects.requireNonNull(inputStream, "资源(" + resourceRelativePath + ")不存在");
		htmlFileResourceResponder.response(inputStream, resourceResponseProperties);
	}

}
