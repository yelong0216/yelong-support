/**
 * 
 */
package org.yelong.support.orm.mybaits.sql.condition;

import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.yelong.core.annotation.Nullable;
import org.yelong.core.jdbc.sql.defaults.DefaultSimpleConditionSqlFragment;
import org.yelong.support.orm.mybaits.sql.MyBatisBoundSql;
import org.yelong.support.orm.mybaits.sql.MyBatisParamMap;
import org.yelong.support.orm.mybaits.sql.MyBatisSqlFragment;

/**
 * @author 彭飞
 * @date 2019年10月24日下午5:47:35
 * @version 1.2
 */
public class MyBatisSimpleConditionFragment extends DefaultSimpleConditionSqlFragment implements MyBatisSqlFragment{

	private final String myBatisConditionFragment;
	
	private MyBatisParamMap MYBATISPARAM = new MyBatisParamMap("MYBATISPARAM");
	
	public MyBatisSimpleConditionFragment(String conditionFragment,@Nullable Object[] params) {
		super(conditionFragment, params);
		if( !ArrayUtils.isEmpty(params)) {
			for (Object obj : params) {
				MYBATISPARAM.addParamMap(obj);
			}
			StringBuilder conditionSqlFragment = new StringBuilder(super.getSqlFragment());
			Set<String> mybatisParamPlaceholderSet = MYBATISPARAM.getPlaceholderParamMap().keySet();
			for (String paramPlaceholder : mybatisParamPlaceholderSet) {
				int index = conditionSqlFragment.indexOf("?");
				conditionSqlFragment.replace(index, index+1, paramPlaceholder);
			}
			this.myBatisConditionFragment = conditionSqlFragment.toString();
		} else {
			this.myBatisConditionFragment = conditionFragment;
		}
		
	}
	
	@Override
	public void setParamAlias(String paramAlias) {
		throw new RuntimeException("暂未实现！");
	}

	@Override
	public MyBatisBoundSql getMyBatisBoundSql() {
		return new MyBatisBoundSql(getMyBatisSqlFragment(), getMyBatisParamMap());
	}

	@Override
	public String getMyBatisSqlFragment() {
		return myBatisConditionFragment;
	}

	@Override
	public MyBatisParamMap getMyBatisParamMap() {
		return MYBATISPARAM;
	}



}
