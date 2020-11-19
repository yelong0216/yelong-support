package org.yelong.support.servlet.resource.response;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.yelong.commons.lang.CharsetUtilsE;
import org.yelong.core.annotation.Nullable;
import org.yelong.core.resource.ScopeResourceSupplierManager;
import org.yelong.support.servlet.resource.response.responder.CustomResourceResponder;
import org.yelong.support.servlet.resource.response.responder.DefaultResourceResponder;
import org.yelong.support.servlet.resource.response.responder.ResourceNotFoundResponder;
import org.yelong.support.servlet.resource.response.responder.ResourceResponder;
import org.yelong.support.servlet.resource.response.responder.file.FileResourceResponder;

/**
 * 抽象的资源响应处理器
 * 
 * @since 2.2
 */
public abstract class AbstractResourceResponseHandler implements ResourceResponseHandler {

	// 文件资源响应器
	private Map<String, FileResourceResponder> fileResourceResponderMap = new HashMap<>();

	// 定制的资源响应器
	private Map<String, CustomResourceResponder> customResourceResponderMap = new HashMap<>();

	// 资源未定义响应器
	private ResourceNotFoundResponder resourceNotFoundResponder;

	// 默认的资源响应器。找不到指定类型的响应器时使用
	private DefaultResourceResponder defaultResourceResponder;

	// 资源供应商管理器
	private ScopeResourceSupplierManager scopeResourceSupplierManager;

	public AbstractResourceResponseHandler(ScopeResourceSupplierManager scopeResourceSupplierManager) {
		this.scopeResourceSupplierManager = scopeResourceSupplierManager;
	}

	@Override
	public void handle(ResourceResponseProperties resourceResponseProperties)
			throws ResourceResponseException, IOException {
		try {
			InputStream requestResourceInputStream = getRequestResourceInputStream(resourceResponseProperties);
			if (null == requestResourceInputStream) {
				resourceNotFoundHandle(resourceResponseProperties);
			} else {
				resourceResponseHandle(resourceResponseProperties, requestResourceInputStream);
			}
		} catch (Exception e) {
			throw new ResourceResponseException(e);
		}
	}

	// ==================================================getResourceInputStream==================================================

	/**
	 * 获取请求的资源流
	 * 
	 * @param resourceResponseProperties 资源响应配置属性
	 * @return 请求的资源流。如果请求的资源流不存在则返回 <code>null</code>
	 */
	@Nullable
	protected InputStream getRequestResourceInputStream(ResourceResponseProperties resourceResponseProperties)
			throws ResourceResponseException, IOException {
		String requestURI = resourceResponseProperties.getRequest().getRequestURI();
		requestURI = URLDecoder.decode(requestURI, CharsetUtilsE.UTF_8);
		return scopeResourceSupplierManager.getResourceAsStream(requestURI);
	}

	// ==================================================responseResource==================================================

	/**
	 * 资源不存在处理
	 * 
	 * @param resourceResponseProperties 资源响应配置属性
	 */
	protected void resourceNotFoundHandle(ResourceResponseProperties resourceResponseProperties)
			throws ResourceResponseException, IOException {
		if (null == resourceNotFoundResponder) {
			throw new ResourceResponseException("未找到资源未定义时的处理器");
		}
		resourceNotFoundResponder.response(null, resourceResponseProperties);
	}

	/**
	 * 资源响应处理
	 * 
	 * @param resourceResponseProperties 资源响应配置属性
	 * @param resourceInputStream        资源流
	 */
	protected void resourceResponseHandle(ResourceResponseProperties resourceResponseProperties,
			InputStream resourceInputStream) throws ResourceResponseException, IOException {
		ResourceResponder resourceResponder = null;
		
		//1、定制的资源响应器
		HttpServletRequest request = resourceResponseProperties.getRequest();
		String requestURI = request.getRequestURI();
		resourceResponder = customResourceResponderMap.get(requestURI);
		
		//2、根据文件类型寻找资源响应器
		if (null == resourceResponder) {
			String resourcePath = resourceResponseProperties.getRequestResourcePath();
			String extension = FilenameUtils.getExtension(resourcePath);
			if (StringUtils.isNotBlank(extension)) {
				resourceResponder = fileResourceResponderMap.get(extension);
			}
		}
		
		//3、采用默认的资源响应器
		if (null == resourceResponder) {
			resourceResponder = defaultResourceResponder;
		}
		if (null == resourceResponder) {
			throw new ResourceResponseException("没有对资源(" + requestURI + ")找到合适的响应器");
		}
		resourceResponder.response(resourceInputStream, resourceResponseProperties);
	}

	// ==================================================FileResourceResponder==================================================

	@Override
	public void registerFileResourceResponder(FileResourceResponder fileResourceResponders) {
		String[] supportResourceTypes = fileResourceResponders.getSupportResourceTypes();
		for (String resourceType : supportResourceTypes) {
			if (fileResourceResponderMap.containsKey(resourceType)) {
				// 如果被替换掉应该记录日志
			}
			fileResourceResponderMap.put(resourceType, fileResourceResponders);
		}
	}

	@Override
	public List<FileResourceResponder> getFileResourceResponders() {
		return fileResourceResponderMap.values().stream().collect(Collectors.toList());
	}

	// ==================================================CustomResourceResponder==================================================

	@Override
	public void registerCustomResourceResponder(CustomResourceResponder customResourceResponder) {
		String requestURI = customResourceResponder.getRequestURI();
		if (customResourceResponderMap.containsKey(requestURI)) {
			// 记录日志
		}
		customResourceResponderMap.put(requestURI, customResourceResponder);
	}

	@Override
	public List<CustomResourceResponder> getCustomResourceResponders() {
		return customResourceResponderMap.values().stream().collect(Collectors.toList());
	}

	// ==================================================ResourceNotFoundResponder==================================================

	@Override
	public void setResourceNotFoundResponder(ResourceNotFoundResponder resourceNotFoundResponder) {
		this.resourceNotFoundResponder = resourceNotFoundResponder;
	}

	@Override
	public ResourceNotFoundResponder getResourceNotFoundResponder() {
		return this.resourceNotFoundResponder;
	}

	// ==================================================DefaultResourceResponder==================================================

	@Override
	public void setDefaultResourceResponder(DefaultResourceResponder defaultResourceResponder) {
		this.defaultResourceResponder = defaultResourceResponder;
	}

	@Override
	public DefaultResourceResponder getDefaultResourceResponder() {
		return this.defaultResourceResponder;
	}

	// ==================================================ScopeResourceSupplierManager==================================================

	@Override
	public ScopeResourceSupplierManager getScopeResourceSupplierManager() {
		return this.scopeResourceSupplierManager;
	}

}
