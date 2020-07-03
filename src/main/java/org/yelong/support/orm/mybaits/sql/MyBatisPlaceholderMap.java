/**
 * 
 */
package org.yelong.support.orm.mybaits.sql;

import org.yelong.core.annotation.Nullable;

/**
 * MyBatis动态sql片段
 * 
 * @author PengFei
 */
public interface MyBatisPlaceholderMap extends MyBatisSqlFragment {

	/**
	 * 获取所有MyBatis中使用的占位符
	 * 
	 * @return 动态sql的所有片段占位符
	 */
	@Nullable
	String[] getMyBatisPlaceholderAll();

	/**
	 * 根据片段占位符获取占位符的值<br/>
	 * placeholder如同${} 或者 #{} 中使用的参数<br/>
	 * 
	 * @param placeholder 片段占位符
	 * @return 对应placeholder占位符的值
	 */
	@Nullable
	Object getMyBatisPlaceholderValue(String placeholder);

	/**
	 * 获取MyBatis参数映射
	 * 
	 * @return 获取此MyBatis sql 片段对应的参数映射
	 * @deprecated 不具有稳定性，无法保证sql与参数映射正确关系。不推荐使用 请使用
	 *             {@link #getMyBatisBoundSql()#getMyBatisParamMap()}
	 */
	@Deprecated
	MyBatisParamMap getMyBatisParamMap();

}
