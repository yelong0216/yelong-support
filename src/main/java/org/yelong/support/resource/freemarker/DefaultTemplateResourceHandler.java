package org.yelong.support.resource.freemarker;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import org.yelong.support.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 默认的模板资源处理器
 * 
 * @since 2.2
 */
public class DefaultTemplateResourceHandler extends AbstractTemplateResourceHandler {

	public DefaultTemplateResourceHandler() {
		addTemplateParameterProcessor(x -> x);
	}

	@Override
	public String handle(String templateCode, Map<String, Object> params, Configuration configuration)
			throws IOException, TemplateException {
		Objects.requireNonNull(params);
		if (isNextHandleWhetherProcessParam()) {
			params = processTemplateParameter(params);
		}
		setNextHandleWhetherProcessParam(defaultBextHandleWhetherProcessParam);// 初始化下次处理是否需要加工参数
		configuration.setClassicCompatible(true);// 默认设置 true。允许空值参数
		Template template = FreeMarkerTemplateUtils.buildTemplateByString(templateCode, configuration);
		String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
		return result;
	}

}
