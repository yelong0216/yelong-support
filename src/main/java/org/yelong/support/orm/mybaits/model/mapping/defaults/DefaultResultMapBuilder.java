/**
 * 
 */
package org.yelong.support.orm.mybaits.model.mapping.defaults;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.builder.ResultMapResolver;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.Configuration;
import org.yelong.core.model.resolve.ModelAndTable;
import org.yelong.support.orm.mybaits.model.mapping.ResultMapBuilder;
import org.yelong.support.orm.mybaits.model.mapping.ResultMappingBuilder;

/**
 * @author PengFei
 */
public class DefaultResultMapBuilder implements ResultMapBuilder{

	private static final AtomicInteger NUMBER_COUNTER = new AtomicInteger(0);
	
	private final ResultMappingBuilder resultMappingBuilder;
	
	public DefaultResultMapBuilder(final ResultMappingBuilder resultMappingBuilder) {
		this.resultMappingBuilder = resultMappingBuilder;
	}

	@Override
	public List<ResultMap> build(ModelAndTable modelAndTable, Configuration configuration) {
		Class<?> type = modelAndTable.getModelClass();
		String resource = type.getName().replace('.', '/') + ".java (best guess)";
		MapperBuilderAssistant assistant = new MapperBuilderAssistant(configuration, resource);
		return build(modelAndTable,assistant);
	}

	@Override
	public List<ResultMap> build(ModelAndTable modelAndTable, MapperBuilderAssistant assistant) {
		Class<?> type = modelAndTable.getModelClass();
		List<ResultMapping> resultMappings = resultMappingBuilder.build(modelAndTable, assistant.getConfiguration());
		String id = getCustomIdentifier(type);
		ResultMapResolver resultMapResolver = new ResultMapResolver(assistant, id, type, null, null, resultMappings, null);
		List<ResultMap> resultMaps = new ArrayList<ResultMap>();
		resultMaps.add(resultMapResolver.resolve());
		return resultMaps;
	}
	
	/**
	 * 自定义标识符
	 * @param type
	 * @return
	 */
	public String getCustomIdentifier(Class<?> type) {
		StringBuilder idBuilder = new StringBuilder();
		idBuilder.append("_[");
		idBuilder.append(type.getSimpleName());
		idBuilder.append("]_");
		idBuilder.append(NUMBER_COUNTER.getAndIncrement());
		return idBuilder.toString();
	}
	
}
