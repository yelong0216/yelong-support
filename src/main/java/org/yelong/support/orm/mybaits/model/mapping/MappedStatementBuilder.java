/**
 * 
 */
package org.yelong.support.orm.mybaits.model.mapping;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.yelong.core.model.manage.ModelAndTable;

/**
 * {@link MappedStatement} 建造者
 * 
 * @since 1.1
 */
public interface MappedStatementBuilder {

	/**
	 * 构建一个查询的 {@link MappedStatement}
	 * 
	 * @param statementId   statementId
	 * @param modelAndTable model and table
	 * @param sqlSource     sqlSource
	 * @param configuration configuration
	 * @return {@link MappedStatement}
	 */
	MappedStatement buildSelect(String statementId, ModelAndTable modelAndTable, SqlSource sqlSource,
			Configuration configuration);

}