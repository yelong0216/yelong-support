package org.yelong.support.resource.freemarker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yelong.commons.util.CollectionUtilsE;

/**
 * 抽象实现
 * 
 * @since 2.2
 */
public abstract class AbstractTemplateResourceHandler implements TemplateResourceHandler {

	protected List<TemplateParameterProcessor> templateParameterProcessors = new ArrayList<TemplateParameterProcessor>();

	protected final ThreadLocal<Boolean> nextHandleWhetherProcessParamThreadLocal = new ThreadLocal<Boolean>();

	protected static final boolean defaultBextHandleWhetherProcessParam = true;
	
	/**
	 * 加工模板参数
	 * 
	 * @param params 模板参数
	 * @return 加工后的模板参数
	 */
	@Override
	public Map<String, Object> processTemplateParameter(Map<String, Object> params) {
		CollectionUtilsE.requireNonEmpty(templateParameterProcessors, "不存在模板参数加工器");
		for (TemplateParameterProcessor templateParameterProcessor : templateParameterProcessors) {
			params = templateParameterProcessor.apply(params);
		}
		return params;
	}

	@Override
	public void addTemplateParameterProcessor(TemplateParameterProcessor templateParameterProcessor) {
		templateParameterProcessors.add(templateParameterProcessor);
	}

	@Override
	public void addTemplateParameterProcessors(List<TemplateParameterProcessor> templateParameterProcessors) {
		templateParameterProcessors.addAll(templateParameterProcessors);
	}

	@Override
	public List<TemplateParameterProcessor> getTemplateParameterProcessors() {
		return templateParameterProcessors;
	}

	@Override
	public void removeTemplateParameterProcessor(TemplateParameterProcessor templateParameterProcessor) {
		templateParameterProcessors.remove(templateParameterProcessor);
	}

	@Override
	public boolean isNextHandleWhetherProcessParam() {
		Boolean value = nextHandleWhetherProcessParamThreadLocal.get();
		return value == null ? true : value;
	}

	@Override
	public void setNextHandleWhetherProcessParam(boolean whether) {
		nextHandleWhetherProcessParamThreadLocal.set(whether);
	}

}
