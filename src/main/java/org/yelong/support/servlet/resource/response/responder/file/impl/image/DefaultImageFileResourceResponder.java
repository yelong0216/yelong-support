package org.yelong.support.servlet.resource.response.responder.file.impl.image;

import java.io.IOException;
import java.io.InputStream;

import org.yelong.support.servlet.resource.response.ResourceResponseException;
import org.yelong.support.servlet.resource.response.ResourceResponseProperties;
import org.yelong.support.servlet.resource.response.responder.file.AbstractFileResourceResponder;
import org.yelong.support.servlet.resource.response.responder.file.FileResourceResponseUtils;

/**
 * 图片
 * 
 * @since 2.2
 */
public class DefaultImageFileResourceResponder extends AbstractFileResourceResponder {

	public DefaultImageFileResourceResponder() {
		addSupportResourceTypes("jpg", "png", "gif");
	}

	@Override
	public void response(InputStream resourceInputStream, ResourceResponseProperties resourceResponseProperties)
			throws ResourceResponseException, IOException {
		FileResourceResponseUtils.responseBytes(resourceInputStream, resourceResponseProperties);
	}

}
