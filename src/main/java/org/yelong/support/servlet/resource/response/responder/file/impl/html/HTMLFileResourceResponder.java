package org.yelong.support.servlet.resource.response.responder.file.impl.html;

import org.yelong.support.servlet.resource.response.responder.file.FileResourceResponder;

/**
 * HTML文件资源响应器
 * 
 * @since 2.2
 */
public interface HTMLFileResourceResponder extends FileResourceResponder {

	String[] htmlFileType = { "html" };

	String htmlContentType = "text/html; charset=utf-8";

}
