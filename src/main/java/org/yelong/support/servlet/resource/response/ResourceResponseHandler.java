/**
 * 
 */
package org.yelong.support.servlet.resource.response;

import java.io.IOException;
import java.util.List;

import org.yelong.core.resource.ScopeResourceSupplierManager;
import org.yelong.support.servlet.resource.response.responder.CustomResourceResponder;
import org.yelong.support.servlet.resource.response.responder.DefaultResourceResponder;
import org.yelong.support.servlet.resource.response.responder.ResourceNotFoundResponder;
import org.yelong.support.servlet.resource.response.responder.file.FileResourceResponder;

/**
 * 资源响应处理器
 * 
 * @since 2.2
 */
public interface ResourceResponseHandler {

	/**
	 * 响应资源
	 * 
	 * @param resourceResponseProperties 资源响应配置属性
	 * @throws ResourceResponseException 资源响应异常
	 * @throws IOException               资源流异常
	 */
	void handle(ResourceResponseProperties resourceResponseProperties) throws ResourceResponseException, IOException;

	// ==================================================FileResourceResponder==================================================

	/**
	 * 注册文件资源响应器
	 * 
	 * @param resourceResponder 注册文件资源响应器
	 */
	void registerFileResourceResponder(FileResourceResponder fileResourceResponder);

	/**
	 * 注册文件资源响应器集合
	 * 
	 * @param fileResourceResponders 注册文件资源响应器集合
	 */
	default void registerFileResourceResponders(List<FileResourceResponder> fileResourceResponders) {
		fileResourceResponders.forEach(this::registerFileResourceResponder);
	}

	/**
	 * @return 所有注册的文件资源响应器
	 */
	List<FileResourceResponder> getFileResourceResponders();

	// ==================================================CustomResourceResponder==================================================

	/**
	 * 注册定制资源响应器
	 * 
	 * @param customResourceResponder 注册定制的资源响应器
	 */
	void registerCustomResourceResponder(CustomResourceResponder customResourceResponder);

	/**
	 * 注册定制的资源响应器集合
	 * 
	 * @param customResourceResponders 注册定制的资源响应器集合
	 */
	default void registerCustomResourceResponders(List<CustomResourceResponder> customResourceResponders) {
		customResourceResponders.forEach(this::registerCustomResourceResponder);
	}

	/**
	 * @return 所有注册的定制的资源响应器
	 */
	List<CustomResourceResponder> getCustomResourceResponders();

	// ==================================================ResourceNotFoundResponder==================================================

	/**
	 * 设置资源未定义时响应器
	 * 
	 * @param resourceNotFoundResponder 资源未定义时响应器
	 */
	void setResourceNotFoundResponder(ResourceNotFoundResponder resourceNotFoundResponder);

	/**
	 * @return 资源未定义时响应器
	 */
	ResourceNotFoundResponder getResourceNotFoundResponder();

	// ==================================================DefaultResourceResponder==================================================

	/**
	 * 设置默认的资源响应器
	 * 
	 * @param defaultResourceResponder 默认的资源响应器
	 */
	void setDefaultResourceResponder(DefaultResourceResponder defaultResourceResponder);

	/**
	 * @return 默认的资源响应器
	 */
	DefaultResourceResponder getDefaultResourceResponder();

	// ==================================================ScopeResourceSupplierManager==================================================

	/**
	 * @return 资源供应商管理器
	 */
	ScopeResourceSupplierManager getScopeResourceSupplierManager();

}
