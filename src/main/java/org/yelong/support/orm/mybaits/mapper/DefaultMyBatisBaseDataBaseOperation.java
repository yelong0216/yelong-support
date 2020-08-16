/**
 * 
 */
package org.yelong.support.orm.mybaits.mapper;

import org.apache.ibatis.session.SqlSession;
import org.yelong.support.spring.jdbc.mybatis.TransactionalMyBatisBaseDataBaseOperation;

/**
 * 默认的mybatis 基础数据操作
 * 
 * @since 1.0
 */
public class DefaultMyBatisBaseDataBaseOperation extends TransactionalMyBatisBaseDataBaseOperation {

	private final SqlSession sqlSession;

	public DefaultMyBatisBaseDataBaseOperation(final SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public SqlSession getSqlSession() {
		return sqlSession;
	}

}
