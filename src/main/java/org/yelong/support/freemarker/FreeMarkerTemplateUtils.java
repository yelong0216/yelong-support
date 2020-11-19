package org.yelong.support.freemarker;

import java.io.IOException;
import java.io.StringWriter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 模板工具类
 * 
 * @since 2.2
 */
public final class FreeMarkerTemplateUtils {

	private FreeMarkerTemplateUtils() {
	}

	/**
	 * 加工模板信息字符串
	 * 
	 * @param template 模板
	 * @param model    数据模型
	 * @return 加工处理后的字符串
	 */
	public static String processTemplateIntoString(Template template, Object model)
			throws IOException, TemplateException {
		StringWriter result = new StringWriter();
		template.process(model, result);
		return result.toString();
	}

	/**
	 * 根据字符串构建模板
	 * 
	 * @param templateCode 模板代码字符串
	 * @return 模板
	 */
	public static Template buildTemplateByString(String templateCode) throws IOException {
		return buildTemplateByString(templateCode, new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
	}

	/**
	 * 根据字符串构建模板
	 * 
	 * @param templateCode  模板代码字符串
	 * @param configuration 模板配置
	 * @return 模板
	 */
	public static Template buildTemplateByString(String templateCode, Configuration configuration) throws IOException {
		return new Template(null, templateCode, configuration);
	}

}
