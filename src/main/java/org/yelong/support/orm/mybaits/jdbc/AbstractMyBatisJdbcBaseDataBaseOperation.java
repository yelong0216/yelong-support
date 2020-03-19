/**
 * 
 */
package org.yelong.support.orm.mybaits.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.yelong.core.jdbc.AbstractBaseDataBaseOperation;
import org.yelong.core.jdbc.DataBaseOperationType;

/**
 * 抽象mybatis基础数据库操作
 * 通过mybatis获取原生Connection来进行操作
 * 这个不会经过mybatis的框架
 * @author 彭飞
 * @date 2019年8月14日上午11:15:55
 * @version 1.0
 */
public abstract class AbstractMyBatisJdbcBaseDataBaseOperation extends AbstractBaseDataBaseOperation{
	
	@Override
	public Connection getConnection() {
		try {
			return getSqlSession().getConfiguration().getEnvironment().getDataSource().getConnection();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public void afterExecute(Connection conn, Object result, DataBaseOperationType operationType) throws SQLException {
		conn.close();
	}
	
	/**
	 * 获取SqlSession
	 * @author 彭飞
	 * @date 2019年8月14日上午11:17:48
	 * @version 1.0
	 * @return
	 */
	public abstract SqlSession getSqlSession();
	
	
}
