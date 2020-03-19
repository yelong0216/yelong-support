/**
 * 
 */
package org.yelong.support.orm.mybaits.mapper;

import org.apache.ibatis.session.SqlSession;

/**
 * @author pengfei<yl1430834495@163.com>
 * @date 2019年11月5日下午2:52:53
 * @version 1.2
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
