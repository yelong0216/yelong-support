/**
 * 
 */
package org.yelong.support.orm.mybaits.model.mapping.defaults;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.ResultFlag;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.ibatis.type.UnknownTypeHandler;
import org.yelong.commons.lang.EnumUtilsE;
import org.yelong.core.model.manage.FieldAndColumn;
import org.yelong.core.model.manage.FieldAndColumnType;
import org.yelong.core.model.manage.ModelAndTable;
import org.yelong.support.orm.mybaits.model.mapping.ResultMappingBuilder;

/**
 * @since 1.1
 */
public class DefaultResultMappingBuilder implements ResultMappingBuilder {

	@Override
	public List<ResultMapping> build(ModelAndTable modelAndTable, Configuration configuration) {
		List<ResultMapping> resultMappings = new ArrayList<>();
		modelAndTable.getFieldAndColumns(FieldAndColumnType.ORDINARY,FieldAndColumnType.PRIMARYKEY,FieldAndColumnType.EXTEND).stream().filter(FieldAndColumn::isSelect).forEach(x -> {
			List<ResultFlag> flags = new ArrayList<>();
			// String columnName = x.getColumn();
			String selectColumnName = x.getSelectColumn();
			String propertyName = x.getFieldName();
			String nestedResultMapId = null;
			if (x.isPrimaryKey()) {
				flags.add(ResultFlag.ID);
			}
			TypeHandler<?> typeHandler = resolveTypeHandler(x.getFieldType(), null, configuration);
			String jdbcTypeStr = x.getJdbcType();
			JdbcType jdbcType = StringUtils.isEmpty(jdbcTypeStr) ? null
					: EnumUtilsE.valueOf(JdbcType.class, jdbcTypeStr);// 如果jdbcType没有映射的枚举则返回null
			ResultMapping resultMapping = new ResultMapping.Builder(configuration, propertyName, selectColumnName,
					x.getFieldType()).flags(flags).jdbcType(jdbcType).nestedResultMapId(nestedResultMapId)
					.typeHandler(typeHandler).build();
			resultMappings.add(resultMapping);
		});
		return resultMappings;
	}

	/**
	 * 解析类型处理器
	 * 
	 * @return
	 */
	public TypeHandler<?> resolveTypeHandler(Class<?> javaType, Class<? extends TypeHandler<?>> typeHandlerType,
			Configuration configuration) {
		if (null == typeHandlerType || typeHandlerType == UnknownTypeHandler.class) {
			return null;
		}
		TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
		TypeHandler<?> handler = typeHandlerRegistry.getMappingTypeHandler(typeHandlerType);
		if (null == handler) {
			handler = typeHandlerRegistry.getInstance(javaType, typeHandlerType);
		}
		return handler;
	}

}
