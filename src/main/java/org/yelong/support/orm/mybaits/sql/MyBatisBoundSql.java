/**
 * 
 */
package org.yelong.support.orm.mybaits.sql;

import org.yelong.core.annotation.Nullable;

/**
 * MyBatis类型的绑定的sql
 */
public class MyBatisBoundSql {

	private final String myBatisSql;

	@Nullable
	private final MyBatisParamMap mybatisParamMap;

	public MyBatisBoundSql(String myBatisSql, @Nullable MyBatisParamMap myBatisParamMap) {
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
		print.append("-------sql : " + myBatisSql);
		print.append("\n");
		print.append("-------param : " + mybatisParamMap.toString());
		return print.toString();
	}

}
