package org.yelong.support.resource.freemarker;

import java.util.Map;
import java.util.function.Function;

/**
 * 模板参数供加工器。将传入的参数加工为模板使用的参数。目前只支持Map形式的
 * 
 * @since 2.2
 */
public interface TemplateParameterProcessor extends Function<Map<String, Object>, Map<String, Object>> {

}
