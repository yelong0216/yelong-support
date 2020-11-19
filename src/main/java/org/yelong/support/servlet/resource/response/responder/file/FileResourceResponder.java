package org.yelong.support.servlet.resource.response.responder.file;

import org.yelong.support.servlet.resource.response.responder.ResourceResponder;

/**
 * 文件资源响应器
 * 
 * @since 2.2
 */
public interface FileResourceResponder extends ResourceResponder {

	/**
	 * @return 该资源相应器所支持的(资源)类型
	 */
	String[] getSupportResourceTypes();

}
