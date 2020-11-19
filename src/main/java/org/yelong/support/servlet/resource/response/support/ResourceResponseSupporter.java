package org.yelong.support.servlet.resource.response.support;

import java.io.IOException;

import org.yelong.support.servlet.resource.response.ResourceResponseException;
import org.yelong.support.servlet.resource.response.ResourceResponseProperties;

/**
 * 资源响应帮助器
 * 
 * @since 2.2
 */
public interface ResourceResponseSupporter {

	String WEB_INF = "WEB-INF";

	/**
	 * 响应HTML资源。先查询WEB-INF中是否存在项目路径的资源，如果存在则响应WEB-INF中的资源，如果不存在则查找指定包下面的资源
	 * 
	 * @param resourceResponseProperties 资源响应配置
	 * @param resourcePackagePath        资源所在的包路径。此路径分隔符可以是 . 和 /
	 * @param resourceRelativePath       资源对于所在的资源包的相对路径
	 */
	void responseHtml(ResourceResponseProperties resourceResponseProperties, String resourcePackagePath,
			String resourceRelativePath) throws ResourceResponseException, IOException;

}
