package org.yelong.support.servlet.resource.response.responder.file.impl.html;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.yelong.support.servlet.resource.response.ResourceResponseException;
import org.yelong.support.servlet.resource.response.ResourceResponseProperties;
import org.yelong.support.servlet.resource.response.responder.file.AbstractFileResourceResponder;
import org.yelong.support.servlet.resource.response.responder.file.FileResourceResponseUtils;

/**
 * 默认实现
 * 
 * @since 2.2
 */
public class DefaultHTMLFileResourceResponder extends AbstractFileResourceResponder implements HTMLFileResourceResponder{

	public DefaultHTMLFileResourceResponder() {
		addSupportResourceTypes(htmlFileType);
	}

	@Override
	public void response(InputStream resourceInputStream, ResourceResponseProperties resourceResponseProperties)
			throws ResourceResponseException, IOException {
		HttpServletResponse response = resourceResponseProperties.getResponse();
		response.setContentType(htmlContentType);
		FileResourceResponseUtils.responseTextSupport304(resourceInputStream, resourceResponseProperties);
	}

}
