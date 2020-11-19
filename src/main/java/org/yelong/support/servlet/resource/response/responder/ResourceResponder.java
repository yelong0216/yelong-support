/**
 * 
 */
package org.yelong.support.servlet.resource.response.responder;

import java.io.IOException;
import java.io.InputStream;

import org.yelong.core.annotation.Nullable;
import org.yelong.support.servlet.resource.response.ResourceResponseException;
import org.yelong.support.servlet.resource.response.ResourceResponseProperties;

/**
 * 资源响应器
 * 
 * @since 2.2
 */
public interface ResourceResponder {

	/**
	 * 响应资源给前端
	 * 
	 * @param resourceInputStream        资源的流
	 * @param resourceResponseProperties 资源响应配置
	 * @throws ResourceResponseException 资源响应异常
	 * @throws IOException               资源流异常
	 */
	void response(@Nullable InputStream resourceInputStream, ResourceResponseProperties resourceResponseProperties)
			throws ResourceResponseException, IOException;

}
