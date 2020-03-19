/**
 * 
 */
package org.yelong.support.orm.mybaits.sql;

import org.yelong.core.annotation.Nullable;

/**
 * mybatis动态sql片段
 * @author 彭飞
 * @date 2019年8月21日上午10:18:58
 * @version 1.2
 */
public interface MyBatisPlaceholderMap extends MyBatisSqlFragment{

	
	/**
	 * 获取所有mybatis中使用的占位符
	 * @author 彭飞
	 * @date 2019年8月21日上午10:22:11
	 * @version 1.2
	 * @return 动态sql的所有片段占位符
	 */
	@Nullable
	String [] getMyBatisPlaceholderAll();
	
	/**
	 * 根据片段占位符获取占位符的值<br/>
	 * placeholder如同${} 或者 #{} 中使用的参数<br/>
	 * @author 彭飞
	 * @date 2019年8月21日上午10:22:49
	 * @version 1.2
	 * @param placeholder 片段占位符
	 * @return 对应placeholder占位符的值
	 */
	@Nullable
	Object getMyBatisPlaceholderValue(String placeholder);
	
	/**
	 * 获取mybatis参数映射
	 * @author 彭飞
	 * @date 2019年9月3日下午2:07:10
	 * @version 1.2
	 * @return 获取此mybatis sql 片段对应的参数映射
	 * @deprecated 不具有稳定性，无法保证sql与参数映射正确关系。不推荐使用
	 * 				请使用 {@link #getMyBatisBoundSql()#getMyBatisParamMap()}
	 */
	@Deprecated
	MyBatisParamMap getMyBatisParamMap();
	
}
