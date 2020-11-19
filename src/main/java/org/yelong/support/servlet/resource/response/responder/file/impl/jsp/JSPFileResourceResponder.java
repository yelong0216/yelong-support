/**
 * 
 */
package org.yelong.support.servlet.resource.response.responder.file.impl.jsp;

import org.yelong.support.servlet.resource.response.responder.file.TemplateFileResourceResponder;

/**
 * JSP文件资源响应器
 * 
 * @since 2.2
 */
public interface JSPFileResourceResponder extends TemplateFileResourceResponder {

	String jspFileType = "jsp";

	String jspContentType = "text/html; charset=utf-8";

}
