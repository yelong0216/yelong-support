/**
 * 
 */
package org.yelong.support.orm.mybaits.model.mapping.defaults;

import java.util.List;
import java.util.Objects;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.yelong.core.model.resolve.ModelAndTable;
import org.yelong.support.orm.mybaits.model.mapping.MappedStatementBuilder;
import org.yelong.support.orm.mybaits.model.mapping.ResultMapBuilder;

/**
 * @author PengFei
 */
public class DefaultMappedStatementBuilder implements MappedStatementBuilder {

	private ResultMapBuilder resultMapBuilder;

	public DefaultMappedStatementBuilder(ResultMapBuilder resultMapBuilder) {
		Objects.requireNonNull(resultMapBuilder);
		this.resultMapBuilder = resultMapBuilder;
	}

	@Override
	public MappedStatement buildSelect(String statementId, ModelAndTable modelAndTable, SqlSource sqlSource,
			Configuration configuration) {
		Objects.requireNonNull(statementId);
		MappedStatement.Builder builder = new MappedStatement.Builder(configuration, statementId, sqlSource,
				SqlCommandType.SELECT);
		List<ResultMap> resultMaps = resultMapBuilder.build(modelAndTable, configuration);
		builder.resultMaps(resultMaps);
		return builder.build();
	}

	public ResultMapBuilder getResultMapBuilder() {
		return resultMapBuilder;
	}

	public void setResultMapBuilder(ResultMapBuilder resultMapBuilder) {
		this.resultMapBuilder = resultMapBuilder;
	}

}
