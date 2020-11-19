package org.yelong.support.resource.freemarker;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.yelong.commons.io.IOUtilsE;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 模板文件资源处理器。返回解析后的内容
 * 
 * @see Template
 * @since 2.2
 */
public interface TemplateResourceHandler {

	Configuration DEFAULT_CONFIGURATION = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

	/**
	 * 处理模板资源流
	 * 
	 * @param inputStream 流
	 * @return 加工模板后的字符串结果
	 */
	default String handle(InputStream inputStream) throws IOException, TemplateException {
		return handle(inputStream, Collections.emptyMap());
	}

	/**
	 * 处理模板资源流
	 * 
	 * @param inputStream 流
	 * @param params      模板使用的参数
	 * @return 加工模板后的字符串结果
	 */
	default String handle(InputStream inputStream, Map<String, Object> params) throws IOException, TemplateException {
		return handle(IOUtilsE.readString(inputStream), params);
	}

	/**
	 * 处理模板代码。加工模板代码
	 * 
	 * @param templateCode 模板代码字符串
	 * @return 加工模板后的字符串结果
	 */
	default String handle(String templateCode) throws IOException, TemplateException {
		return handle(templateCode, Collections.emptyMap());
	}

	/**
	 * 处理模板代码。加工模板代码
	 * 
	 * @param templateCode 模板代码字符串
	 * @param params       模板使用的参数
	 * @return 加工模板后的字符串结果
	 */
	default String handle(String templateCode, Map<String, Object> params) throws IOException, TemplateException {
		return handle(templateCode, params, DEFAULT_CONFIGURATION);
	}

	/**
	 * 处理模板代码。加工模板代码
	 * 
	 * @param templateCode  模板代码字符串
	 * @param params        模板使用的参数。这个参数会通过加工之后在使用
	 * @param configuration 模板配置
	 * @return 加工模板后的字符串结果
	 */
	String handle(String templateCode, Map<String, Object> params, Configuration configuration)
			throws IOException, TemplateException;

	/**
	 * 设置下一次处理是否需要加工参数
	 * 
	 * @param whether 是否需要加工参数
	 */
	void setNextHandleWhetherProcessParam(boolean whether);

	/**
	 * 加工模板参数
	 * 
	 * @param params 参数
	 * @return 加工后的模板参数
	 */
	Map<String, Object> processTemplateParameter(Map<String, Object> params);

	/**
	 * 获取下一次处理是否需要加工参数。如果没有设置是否需要加工，则默认需要加工
	 * 
	 * @return 获取下一次处理是否需要加工参数
	 */
	boolean isNextHandleWhetherProcessParam();

	/**
	 * 添加模板参数加工器
	 * 
	 * @param templateParameterProcessor 模板参数加工器
	 */
	void addTemplateParameterProcessor(TemplateParameterProcessor templateParameterProcessor);

	/**
	 * 添加模板参数加工器
	 * 
	 * @param templateParameterProcessors 模板参数加工器
	 */
	void addTemplateParameterProcessors(List<TemplateParameterProcessor> templateParameterProcessors);

	/**
	 * 获取所有的模板参数加工器
	 * 
	 * @return 模板参数加工器
	 */
	List<TemplateParameterProcessor> getTemplateParameterProcessors();

	/**
	 * 移除模板参数加工器
	 * 
	 * @param templateParameterProcessor 模板参数加工器
	 */
	void removeTemplateParameterProcessor(TemplateParameterProcessor templateParameterProcessor);

}
