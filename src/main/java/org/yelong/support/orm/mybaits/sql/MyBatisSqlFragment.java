/**
 * 
 */
package org.yelong.support.orm.mybaits.sql;

import org.yelong.core.jdbc.sql.SqlFragment;

/**
 * mybatis sql片段
 * 
 * @since 1.0
 */
public interface MyBatisSqlFragment extends SqlFragment, MyBatisParamAliasable {

	/**
	 * 可用于mybatis执行的sql 占位符由?替换为#{} 推荐使用{@link #getMyBatisBoundSql()}保持参数与sql的映射统一
	 * 
	 * @return mybatis sql片段
	 */
	String getMyBatisSqlFragment();

	/**
	 * 推荐使用{@link #getMyBatisBoundSql()}保持参数与sql的映射统一
	 * 
	 * @return mybatis执行的参数映射
	 */
	MyBatisParamMap getMyBatisParamMap();

	/**
	 * 获取当前sql(这可能是一个sql片段)与参数的映射
	 * 
	 * @return sql与参数的映射
	 */
	MyBatisBoundSql getMyBatisBoundSql();

}
