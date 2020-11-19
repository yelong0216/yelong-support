package org.yelong.support.servlet.resource.response.responder.file.impl.woff;

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
public class DefaultWoffFileResourceResponder extends AbstractFileResourceResponder {

	public static final String woffContentType = "application/font-woff";

	public DefaultWoffFileResourceResponder() {
		addSupportResourceTypes("woff");
	}

	@Override
	public void response(InputStream resourceInputStream, ResourceResponseProperties resourceResponseProperties)
			throws ResourceResponseException, IOException {
		HttpServletResponse response = resourceResponseProperties.getResponse();
		response.setContentType(woffContentType);
		FileResourceResponseUtils.responseBytesSupport304(resourceInputStream, resourceResponseProperties);
	}

}
