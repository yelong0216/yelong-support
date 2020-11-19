package org.yelong.support.servlet.resource.response.responder;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.yelong.commons.io.IOUtilsE;
import org.yelong.support.servlet.resource.response.ResourceResponseException;
import org.yelong.support.servlet.resource.response.ResourceResponseProperties;

/**
 * 默认的文件响应器
 * 
 * @since 2.2
 */
public class DefaultDefaultResourceResponder implements DefaultResourceResponder {

	@Override
	public void response(InputStream resourceInputStream, ResourceResponseProperties resourceResponseProperties)
			throws ResourceResponseException, IOException {
		HttpServletResponse response = resourceResponseProperties.getResponse();
		String text = IOUtilsE.readString(resourceInputStream);
		IOUtilsE.closeQuietly(resourceInputStream);
		response.getWriter().write(text);
	}

}
