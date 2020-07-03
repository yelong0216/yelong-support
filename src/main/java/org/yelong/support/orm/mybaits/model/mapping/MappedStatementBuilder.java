/**
 * 
 */
package org.yelong.support.orm.mybaits.model.mapping;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.yelong.core.model.resolve.ModelAndTable;

/**
 * {@link MappedStatement} 建造者
 * 
 * @author PengFei
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