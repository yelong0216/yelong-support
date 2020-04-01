/**
 * 
 */
package org.yelong.support.orm.mybaits.model.mapping;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.yelong.core.model.resolve.ModelAndTable;

/**
 * @author PengFei
 *
 */
public interface MappedStatementBuilder {

	MappedStatement buildSelect(String statementId , ModelAndTable modelAndTable, SqlSource sqlSource , Configuration configuration);
	
}