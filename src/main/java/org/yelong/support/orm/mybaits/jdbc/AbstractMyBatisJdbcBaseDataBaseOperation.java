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
 * 抽象mybatis基础数据库操作<br/>
 * 
 * 通过mybatis获取原生Connection来进行操作<br/>
 * 
 * 这个不会经过mybatis的框架
 * 
 * @author PengFei
 */
public abstract class AbstractMyBatisJdbcBaseDataBaseOperation extends AbstractBaseDataBaseOperation {

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
	 * @return sql session
	 */
	public abstract SqlSession getSqlSession();

}
