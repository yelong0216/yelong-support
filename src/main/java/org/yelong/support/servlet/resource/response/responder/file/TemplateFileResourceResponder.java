package org.yelong.support.servlet.resource.response.responder.file;

import org.yelong.support.resource.freemarker.TemplateResourceHandler;

/**
 * 模板文件资源响应器。这个资源是可以通过Freemarker模板进行加功能的。
 * 
 * @since 2.2
 */
public interface TemplateFileResourceResponder extends FileResourceResponder {

	/**
	 * 这个参数是调用
	 * {@link TemplateResourceHandler#handle(String, java.util.Map, freemarker.template.Configuration)}时传递的参数中一个键值对的参数名称
	 */
	String resourceResponsePropertiesParamName = "resourceResponseProperties";

	/**
	 * 获取模板资源处理器
	 * 
	 * @return 模板资源处理器
	 */
	TemplateResourceHandler getTemplateResourceHandler();

}
