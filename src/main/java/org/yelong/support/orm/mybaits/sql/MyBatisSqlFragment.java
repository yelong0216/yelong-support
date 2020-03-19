/**
 * 
 */
package org.yelong.support.orm.mybaits.sql;

import org.yelong.core.jdbc.sql.SqlFragment;

/**
 * @author 彭飞
 * @date 2019年11月1日上午9:52:34
 * @version 1.2
 */
public interface MyBatisSqlFragment extends SqlFragment,MyBatisParamAliasable{
	
	
	/**
	 * 可用于mybatis执行的sql
	 * 占位符由?替换为#{}
	 * 推荐使用{@link #getMyBatisBoundSql()}保持参数与sql的映射统一
	 * @author 彭飞
	 * @date 2019年11月5日上午10:00:44
	 * @version 1.2
	 * @return mybatis sql片段
	 */
	String getMyBatisSqlFragment();
	
	/**
	 * 推荐使用{@link #getMyBatisBoundSql()}保持参数与sql的映射统一
	 * @author 彭飞
	 * @date 2019年11月5日上午10:01:29
	 * @version 1.2
	 * @return mybatis执行的参数映射
	 */
	MyBatisParamMap getMyBatisParamMap();
	
	/**
	 * 获取当前sql(这可能是一个sql片段)与参数的映射
	 * @author 彭飞
	 * @date 2019年10月21日下午2:58:38
	 * @version 1.2
	 * @return sql与参数的映射
	 */
	MyBatisBoundSql getMyBatisBoundSql();

}
