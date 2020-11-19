package org.yelong.support.servlet.resource.response.responder.file.impl.css;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.yelong.support.servlet.resource.response.ResourceResponseException;
import org.yelong.support.servlet.resource.response.ResourceResponseProperties;
import org.yelong.support.servlet.resource.response.responder.file.AbstractFileResourceResponder;
import org.yelong.support.servlet.resource.response.responder.file.FileResourceResponseUtils;

/**
 * css
 * 
 * @since 2.2
 */
public class DefaultCSSFileResourceResponder extends AbstractFileResourceResponder {

	public static final String cssContentType = "text/css;charset=utf-8";

	public DefaultCSSFileResourceResponder() {
		addSupportResourceTypes("css");
	}

	@Override
	public void response(InputStream resourceInputStream, ResourceResponseProperties resourceResponseProperties)
			throws ResourceResponseException, IOException {
		HttpServletResponse response = resourceResponseProperties.getResponse();
		response.setContentType(cssContentType);
		FileResourceResponseUtils.responseTextSupport304(resourceInputStream, resourceResponseProperties);
	}

}
