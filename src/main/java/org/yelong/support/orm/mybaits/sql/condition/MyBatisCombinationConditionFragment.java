/**
 * 
 */
package org.yelong.support.orm.mybaits.sql.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.annotations.Param;
import org.yelong.core.jdbc.sql.condition.ConditionSqlFragment;
import org.yelong.core.jdbc.sql.condition.combination.CombinationConditionSqlFragment;
import org.yelong.core.jdbc.sql.defaults.DefaultCombinationConditionSqlFragment;
import org.yelong.support.orm.mybaits.interceptor.MyBatisPlaceholderMapInterceptor;
import org.yelong.support.orm.mybaits.sql.MyBatisBoundSql;
import org.yelong.support.orm.mybaits.sql.MyBatisParamMap;
import org.yelong.support.orm.mybaits.sql.MyBatisPlaceholderMap;

/**
 * mybatis 组合条件片段<br/>
 * 依赖于jdbc组合条件<br/>
 * <p>
 * 	该对象作为mapper的参数。可以在mapper.xml中使用${condition}获取其条件片段 和${isCondition}来判断是否存在条件片段<br/>
 * 	如果需要使用{@link Param} 应该加入{@link MyBatisPlaceholderMapInterceptor} 拦截器，否则会出现无法获取参数问题
 * </p>
 * @author 彭飞
 * @date 2019年8月29日上午9:34:59
 * @version 1.2
 */
public class MyBatisCombinationConditionFragment extends DefaultCombinationConditionSqlFragment implements MyBatisPlaceholderMap{

	/**
	 * mybatis映射文件中取条件的占位符 。${condition}
	 */
	public static final String CONDITION_FLAG = "condition";

	/**
	 * mybatis映射文件中判断是否存在条件。。&lt;if&gt; test = " isCondition ">${condition}&lt;/if&gt;
	 */
	public static final String IS_CONDITION_FLAG = "isCondition";

	private final MyBatisParamMap MYBATISPARAM = new MyBatisParamMap("MYBATISPARAM");

	private List<MyBatisCombinationConditionFragment> myBatisConditionFragmentFragmentList = new ArrayList<MyBatisCombinationConditionFragment>();

	@Override
	public Object getMyBatisPlaceholderValue(String placeholder) {
		if( placeholder.equalsIgnoreCase(CONDITION_FLAG) ) {
			return getCondition();
		} else if( placeholder.equalsIgnoreCase(IS_CONDITION_FLAG)) {
			return !isEmpty();
		} else {
			return MYBATISPARAM.get(placeholder);
		}
	}

	@Override
	protected void afterAddCondition(ConditionFragmentWrapper conditionFragmentWrapper) {
		super.afterAddCondition(conditionFragmentWrapper);
		ConditionSqlFragment conditionFragment = conditionFragmentWrapper.getConditionFragment();
		//如果为mybatissql则添加其参数映射
		if( conditionFragment instanceof CombinationConditionSqlFragment ) {
			if( conditionFragment instanceof MyBatisCombinationConditionFragment ) {
				MYBATISPARAM.putMyBatisParamMap(((MyBatisCombinationConditionFragment)conditionFragment).getMyBatisParamMap());
				myBatisConditionFragmentFragmentList.add((MyBatisCombinationConditionFragment)conditionFragment);
			} else {
				throw new MyBatisCombinationConditionFragmentException("mybatis组合条件中添加组合条件必须为mybatis的组合条件而不应该为其他类型的组合条件，这可能导致解析为mybatis sql时错误。");
			}
		} else {
			for (Object value : conditionFragment.getParams()) {
				MYBATISPARAM.addParamMap(value);
			}
		}
	}

	/**
	 * 用于mybatis mapper.xml中获取条件语句
	 * @author 彭飞
	 * @date 2019年8月23日下午3:35:35
	 * @version 1.2
	 * @return 条件sql语句
	 */
	public String getCondition() {
		return getSqlFragment();
	}

	@Override
	public String getMyBatisSqlFragment() {
		//修改生成的占位符？为mybatis占位符
		StringBuilder conditionSqlFragment = new StringBuilder(super.getSqlFragment());
		Set<String> mybatisParamPlaceholderSet = MYBATISPARAM.getPlaceholderParamMap().keySet();
		for (String paramPlaceholder : mybatisParamPlaceholderSet) {
			int index = conditionSqlFragment.indexOf("?");
			conditionSqlFragment.replace(index, index+1, paramPlaceholder);
		}
		return conditionSqlFragment.toString();
	}

	@Override
	public void setParamAlias(String paramAlias) {
		this.myBatisConditionFragmentFragmentList.forEach(x->x.setParamAlias(paramAlias));
		this.MYBATISPARAM.setParamAlias(paramAlias);
	}

	@Override
	public MyBatisParamMap getMyBatisParamMap() {
		return MYBATISPARAM;
	}

	/**
	 * 用于mybatis mapper.xml中用来判断是否存在条件<br/>
	 * @author 彭飞
	 * @date 2019年8月23日下午3:34:58
	 * @version 1.2
	 * @return <tt>true</tt> 存在条件
	 */
	public boolean isIsCondition() {
		return !isEmpty();
	}

	@Override
	public String[] getMyBatisPlaceholderAll() {
		return ArrayUtils.toArray(CONDITION_FLAG,IS_CONDITION_FLAG);
	}

	@Override
	public MyBatisBoundSql getMyBatisBoundSql() {
		return new MyBatisBoundSql(getMyBatisSqlFragment(), getMyBatisParamMap());
	}


}
