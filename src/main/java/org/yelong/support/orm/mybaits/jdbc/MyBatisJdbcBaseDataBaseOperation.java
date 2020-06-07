/**
 * 
 */
package org.yelong.support.orm.mybaits.jdbc;

import org.apache.ibatis.session.SqlSession;

/**
 * mybatis jdbc方式的基础数据操作
 * 通过sqlSession获取connection对象进行操作
 * 
 * @author PengFei
 */
public class MyBatisJdbcBaseDataBaseOperation extends AbstractMyBatisJdbcBaseDataBaseOperation{

	private final SqlSession sqlSession;
	
	public MyBatisJdbcBaseDataBaseOperation(final SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public SqlSession getSqlSession() {
		return this.sqlSession;
	}

}
