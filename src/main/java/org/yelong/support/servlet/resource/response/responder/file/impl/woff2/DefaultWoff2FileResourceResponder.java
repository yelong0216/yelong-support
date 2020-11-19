package org.yelong.support.servlet.resource.response.responder.file.impl.woff2;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.yelong.support.servlet.resource.response.ResourceResponseException;
import org.yelong.support.servlet.resource.response.ResourceResponseProperties;
import org.yelong.support.servlet.resource.response.responder.file.AbstractFileResourceResponder;
import org.yelong.support.servlet.resource.response.responder.file.FileResourceResponseUtils;

/**
 * @since 2.2
 */
public class DefaultWoff2FileResourceResponder extends AbstractFileResourceResponder {

	public static final String woff2ContentType = "application/font-woff2";

	public DefaultWoff2FileResourceResponder() {
		addSupportResourceTypes("woff2");
	}

	@Override
	public void response(InputStream resourceInputStream, ResourceResponseProperties resourceResponseProperties)
			throws ResourceResponseException, IOException {
		HttpServletResponse response = resourceResponseProperties.getResponse();
		response.setContentType(woff2ContentType);
		FileResourceResponseUtils.responseBytesSupport304(resourceInputStream, resourceResponseProperties);
	}

}
