/**
 * 
 */
package org.yelong.support.orm.mybaits.mapper;

import org.apache.ibatis.session.SqlSession;

/**
 * 默认的mybatis 基础数据操作
 * @author PengFei
 */
public class DefaultMyBatisBaseDataBaseOperation extends AbstractMyBatisBaseDataBaseOperation{

	private final SqlSession sqlSession;
	
	public DefaultMyBatisBaseDataBaseOperation(final SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public SqlSession getSqlSession() {
		return sqlSession;
	}

}
