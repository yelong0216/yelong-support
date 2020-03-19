/**
 * 
 */
package org.yelong.support.orm.mybaits.sql.attribute;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.yelong.core.jdbc.DataBaseOperationType;
import org.yelong.core.jdbc.sql.defaults.DefaultAttributeSqlFragment;
import org.yelong.support.orm.mybaits.sql.MyBatisBoundSql;
import org.yelong.support.orm.mybaits.sql.MyBatisParamMap;
import org.yelong.support.orm.mybaits.util.MyBatisParamTypeUtils;

/**
 * @author 彭飞
 * @date 2019年9月11日下午6:55:03
 * @version 1.2
 */
public class DefaultMyBatisAttributeFragment extends DefaultAttributeSqlFragment implements MyBatisAttributeFragment{

	/**
	 * mybatis映射文件中取条件的占位符 。${condition}
	 */
	public static final String ATTRIBUTE_FLAG = "attribute";

	private final MyBatisParamMap MYBATISPARAM = new MyBatisParamMap("MYBATISPARAM");

	private String mybatisParamAlias;

	private final Map<String,MyBatisAttribute> myBatisAttributeMap = new LinkedHashMap<>();

	public DefaultMyBatisAttributeFragment(DataBaseOperationType dataBaseOperationType) {
		super(dataBaseOperationType);
	}

	public DefaultMyBatisAttributeFragment() {
		
	}
	
	@Override
	public void addAttr(String attrName, Object value) {
		addAttr(attrName, value, MyBatisParamTypeUtils.getParamTypeMappingMyBatisType(value));
	}

	@Override
	public void addAttr(String attrName, Object value, String jdbcType) {
		super.addAttr(attrName, value);
		myBatisAttributeMap.put(attrName,new MyBatisAttribute(attrName, value, jdbcType));
	}

	@Override
	public boolean addAttrByValueNotNull(String attrName, Object value, String jdbcType) {
		if( null == value ) {
			return false;
		}
		addAttr(attrName, value, jdbcType);
		return true;
	}

	@Override
	public boolean removeAttr(String attrName) {
		myBatisAttributeMap.remove(attrName);
		return super.removeAttr(attrName);
	}

	@Override
	public String[] getMyBatisPlaceholderAll() {
		return new String[] {ATTRIBUTE_FLAG};
	}

	@Override
	public Object getMyBatisPlaceholderValue(String placeholder) {
		if(placeholder.equalsIgnoreCase(ATTRIBUTE_FLAG)) {
			return getSqlFragment();
		}
		return null;
	}

	@Override
	public void setParamAlias(String paramAlias) {
		this.mybatisParamAlias = paramAlias;

	}

	@Override
	public MyBatisParamMap getMyBatisParamMap() {
		throw new UnsupportedOperationException("不支持使用此方法，应使用getMyBatisBoundSql()方法后，在通过其对象获取参数映射。此方法无法保证参数与sql的准确性");
	}

	@Override
	public String getMyBatisSqlFragment() {
		throw new UnsupportedOperationException("不支持使用此方法，应使用getMyBatisBoundSql()方法后，在通过其对象获取参数映射。此方法无法保证参数与sql的准确性");
	}

	protected void flushMyBatisParam() {
		//每次获取sql时在mybatisParam添加参数。之后清楚，防止remove时mybatisParam中清除不掉的问题
		MYBATISPARAM.clear();
		for (MyBatisAttribute myBatisAttribute : myBatisAttributeMap.values()) {
			MYBATISPARAM.addParamMap(myBatisAttribute.getValue(),myBatisAttribute.getJdbcType());
		}
		if(StringUtils.isNotEmpty(mybatisParamAlias)) {
			MYBATISPARAM.setParamAlias(this.mybatisParamAlias);
		}
	}

	@Override
	public MyBatisBoundSql getMyBatisBoundSql() {
		flushMyBatisParam();
		//修改生成的占位符？为mybatis占位符
		StringBuilder sqlFragment = new StringBuilder(super.getSqlFragment());
		Set<String> mybatisParamPlaceholderSet = MYBATISPARAM.getPlaceholderParamMap().keySet();
		for (String paramPlaceholder : mybatisParamPlaceholderSet) {
			int index = sqlFragment.indexOf("?");
			sqlFragment.replace(index, index+1, paramPlaceholder);
		}
		String sql = sqlFragment.toString();
		return new MyBatisBoundSql(sql, MYBATISPARAM);
	}






	/**
	 * mybatis属性pojo类
	 * @author 彭飞
	 * @date 2019年10月21日下午2:29:51
	 * @version 1.2
	 */
	protected static class MyBatisAttribute {

		private final String attributeName;

		private final Object value;

		private final String jdbcType;

		public MyBatisAttribute(String attributeName, Object value, String jdbcType) {
			super();
			this.attributeName = attributeName;
			this.value = value;
			this.jdbcType = jdbcType;
		}

		public String getAttributeName() {
			return attributeName;
		}

		public Object getValue() {
			return value;
		}

		public String getJdbcType() {
			return jdbcType;
		}
	}



}

