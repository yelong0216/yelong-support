package org.yelong.support.servlet.resource.response.responder;

import java.io.IOException;
import java.io.InputStream;

import org.yelong.support.servlet.resource.response.ResourceResponseException;
import org.yelong.support.servlet.resource.response.ResourceResponseProperties;

/**
 * 默认的资源不存在时的响应器。直接响应404
 * 
 * @since 2.2
 */
public class DefaultResourceNotFoundResponder implements ResourceNotFoundResponder {

	@Override
	public void response(InputStream resourceInputStream, ResourceResponseProperties resourceResponseProperties)
			throws ResourceResponseException, IOException {
		resourceResponseProperties.getResponse().setStatus(404);
	}

}
