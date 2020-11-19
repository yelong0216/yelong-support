package org.yelong.support.servlet.resource.response.responder.file.impl.js;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.yelong.support.servlet.resource.response.ResourceResponseException;
import org.yelong.support.servlet.resource.response.ResourceResponseProperties;
import org.yelong.support.servlet.resource.response.responder.file.AbstractFileResourceResponder;
import org.yelong.support.servlet.resource.response.responder.file.FileResourceResponseUtils;

/**
 * JS
 * 
 * @since 2.2
 */
public class DefaultJSFileResourceResponder extends AbstractFileResourceResponder {

	public static final String jsContentType = "text/javascript;charset=utf-8";

	public DefaultJSFileResourceResponder() {
		addSupportResourceTypes("js", "gzjs");
	}

	@Override
	public void response(InputStream resourceInputStream, ResourceResponseProperties resourceResponseProperties)
			throws ResourceResponseException, IOException {
		HttpServletResponse response = resourceResponseProperties.getResponse();
		response.setContentType(jsContentType);
		FileResourceResponseUtils.responseTextSupport304(resourceInputStream, resourceResponseProperties);
	}

}
