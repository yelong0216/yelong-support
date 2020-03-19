/**
 * 
 */
package org.yelong.support.orm.mybaits.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.yelong.core.jdbc.BaseDataBaseOperation;
import org.yelong.support.orm.mybaits.sql.MyBatisParamMap;

/**
 * mybatis 基础数据库操作
 * @author pengfei<yl1430834495@163.com>
 * @date 2019年9月11日下午5:26:57
 * @version 1.2
 */
public interface MyBatisBaseDataBaseOperation extends BaseDataBaseOperation{


	/**
	 * 数据库数据查询<br/>
	 * 一条记录为一个map key:字段名 value：值<br/>
	 * @author pengfei<yl1430834495@163.com>
	 * @date 2019年7月10日下午4:42:17
	 * @param sql 该sql为mybatis类型的sql（参数占位符为#{}类型） sql语句
	 * @param mybatisParamMap 参数 可以为空 如果为空则直接执行sql
	 * @return 查询的数据集合
	 * @throws SQLException
	 */
	List<Map<String,Object>> select(String sql , MyBatisParamMap mybatisParamMap);
	
	/**
	 * 查询一行记录
	 * @author 彭飞
	 * @date 2019年7月14日下午3:13:01
	 * @param sql 该sql为mybatis类型的sql（参数占位符为#{}类型）
	 * @param mybatisParamMap 可以为空 如果为空则直接执行sql
	 * @return 一条数据的键值对
	 * @throws SQLException
	 */
	Map<String,Object> selectRow(String sql,MyBatisParamMap mybatisParamMap );
	
	/**
	 * 查询一列数据
	 * @author 彭飞
	 * @date 2019年7月14日下午3:13:55
	 * @param sql 该sql为mybatis类型的sql（参数占位符为#{}类型）
	 * @param mybatisParamMap 可以为空 如果为空则直接执行sql
	 * @return 数据的集合
	 * @throws SQLException
	 */
	<T> List<T> selectColumn(String sql , MyBatisParamMap mybatisParamMap);
	/**
	 * 查询唯单一的数据值
	 * @author 彭飞
	 * @date 2019年7月14日下午3:11:20
	 * @param <T>
	 * @param sql 该sql为mybatis类型的sql（参数占位符为#{}类型）
	 * @param mybatisParamMap 可以为空 如果为空则直接执行sql
	 * @return
	 * @throws SQLException
	 */
	<T> T selectSingleObject(String sql,MyBatisParamMap mybatisParamMap);
	
	/**
	 * 查询数据库记录数
	 * @author 彭飞
	 * @date 2019年7月10日下午4:47:56
	 * @param sql 该sql为mybatis类型的sql（参数占位符为#{}类型） 查询sql
	 * @param mybatisParamMap 参数 可以为空 如果为空则直接执行sql
	 * @return 查询的数据库记录数
	 * @throws SQLException
	 */
	Long count(String sql , MyBatisParamMap mybatisParamMap);
	
	
	/**
	 * 删除数据库记录
	 * @author 彭飞
	 * @date 2019年7月10日下午4:44:55
	 * @param sql 该sql为mybatis类型的sql（参数占位符为#{}类型） 删除语句
	 * @param mybatisParamMap 参数 可以为空 如果为空则直接执行sql
	 * @return 删除记录的条数
	 * @throws SQLException
	 */
	Integer delete(String sql , MyBatisParamMap mybatisParamMap);
	
	/**
	 * 修改数据库记录
	 * @author 彭飞
	 * @date 2019年7月10日下午4:45:17
	 * @param sql 该sql为mybatis类型的sql（参数占位符为#{}类型） 修改语句
	 * @param mybatisParamMap 参数 可以为空 如果为空则直接执行sql
	 * @return 修改的记录数
	 * @throws SQLException
	 */
	Integer update(String sql , MyBatisParamMap mybatisParamMap);
	
	/**
	 * 添加数据库记录
	 * @author 彭飞
	 * @date 2019年7月10日下午4:45:39
	 * @param sql 该sql为mybatis类型的sql（参数占位符为#{}类型） 添加语句
	 * @param mybatisParamMap 参数 可以为空 如果为空则直接执行sql
	 * @return 添加的记录数
	 * @throws SQLException
	 */
	Integer insert(String sql , MyBatisParamMap mybatisParamMap);
	
	
	
	/**
	 * 获取mybatis数据库操作对象
	 * @author 彭飞
	 * @date 2019年9月30日上午11:15:38
	 * @version 1.2
	 * @return
	 */
	SqlSession getSqlSession();
	
	
}
