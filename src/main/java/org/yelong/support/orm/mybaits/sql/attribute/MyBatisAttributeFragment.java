/**
 * 
 */
package org.yelong.support.orm.mybaits.sql.attribute;

import org.yelong.core.jdbc.sql.attribute.AttributeSqlFragment;
import org.yelong.support.orm.mybaits.sql.MyBatisPlaceholderMap;

/**
 * @author 彭飞
 * @date 2019年9月29日下午2:49:14
 * @version 1.2
 */
public interface MyBatisAttributeFragment extends AttributeSqlFragment,MyBatisPlaceholderMap{

	
	/**
	 * 添加属性<br/>
	 * 注意：如果attrName属性已存在，name将会替换掉之前的
	 * @author 彭飞
	 * @date 2019年9月17日下午2:36:11
	 * @version 1.2
	 * @param attrName 属性名称
	 * @param value 值
	 * @param jdbcType jdbc类型。如：String : VARCHAR
	 */
	void addAttr(String attrName,Object value,String jdbcType);
	
	/**
	 * 添加属性，仅当value不为null时添加
	 * @author 彭飞
	 * @date 2019年9月17日下午2:37:35
	 * @version 1.2
	 * @param attrName 属性名称
	 * @param value 值
	 * @param jdbcType jdbc类型。 如：String : VARCHAR
	 * @return <tt>true</tt> value != null
	 */
	boolean addAttrByValueNotNull(String attrName , Object value , String jdbcType);
	
	
	
}
