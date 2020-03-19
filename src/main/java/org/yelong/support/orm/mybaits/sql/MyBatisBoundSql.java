/**
 * 
 */
package org.yelong.support.orm.mybaits.sql;

import org.yelong.core.annotation.Nullable;

/**
 * mybatis类型的绑定的sql
 * 
 * @author 彭飞
 * @date 2019年10月21日下午2:55:57
 * @version 1.2
 */
public class MyBatisBoundSql{

	private final String myBatisSql;
	
	@Nullable
	private final MyBatisParamMap mybatisParamMap;
	
	public MyBatisBoundSql(String myBatisSql,@Nullable MyBatisParamMap myBatisParamMap) {
		//super(sql,myBatisParamMap == null ? ArrayUtils.EMPTY_OBJECT_ARRAY: myBatisParamMap.values().toArray());
		this.myBatisSql = myBatisSql;
		this.mybatisParamMap = myBatisParamMap;
	}

	public String getMyBatisSql() {
		return myBatisSql;
	}

	public MyBatisParamMap getMybatisParamMap() {
		return mybatisParamMap;
	}
	
	@Override
	public String toString() {
		StringBuilder print = new StringBuilder();
		print.append("-------sql : "+myBatisSql);
		print.append("\n");
		print.append("-------param : "+ mybatisParamMap.toString());
		return print.toString();
	}
	

}
