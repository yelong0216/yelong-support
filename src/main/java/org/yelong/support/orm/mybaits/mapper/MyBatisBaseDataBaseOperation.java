/**
 * 
 */
package org.yelong.support.orm.mybaits.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.yelong.core.jdbc.BaseDataBaseOperation;
import org.yelong.support.orm.mybaits.sql.MyBatisParamMap;

/**
 * mybatis 基础数据库操作
 * 
 * @author PengFei
 */
public interface MyBatisBaseDataBaseOperation extends BaseDataBaseOperation{

	/**
	 * 数据库数据查询<br/>
	 * 一条记录为一个map key:字段名 value：值<br/>
	 * 
	 * @param sql 该sql为mybatis类型的sql（参数占位符为#{}类型） sql语句
	 * @param mybatisParamMap 参数 可以为空 如果为空则直接执行sql
	 * @return 查询的数据集合
	 */
	List<Map<String,Object>> select(String sql , MyBatisParamMap mybatisParamMap);
	
	/**
	 * 查询一行记录
	 * 
	 * @param sql 该sql为mybatis类型的sql（参数占位符为#{}类型）
	 * @param mybatisParamMap 可以为空 如果为空则直接执行sql
	 * @return 一条数据的键值对
	 */
	Map<String,Object> selectRow(String sql,MyBatisParamMap mybatisParamMap );
	
	/**
	 * 查询一列数据
	 * 
	 * @param sql 该sql为mybatis类型的sql（参数占位符为#{}类型）
	 * @param mybatisParamMap 可以为空 如果为空则直接执行sql
	 * @return 数据的集合
	 */
	<T> List<T> selectColumn(String sql , MyBatisParamMap mybatisParamMap);
	
	/**
	 * 查询唯单一的数据值
	 * 
	 * @param <T> data type
	 * @param sql 该sql为mybatis类型的sql（参数占位符为#{}类型）
	 * @param mybatisParamMap 可以为空 如果为空则直接执行sql
	 * @return 查询的唯一值
	 */
	<T> T selectSingleObject(String sql,MyBatisParamMap mybatisParamMap);
	
	/**
	 * 查询数据库记录数
	 * 
	 * @param sql 该sql为mybatis类型的sql（参数占位符为#{}类型） 查询sql
	 * @param mybatisParamMap 参数 可以为空 如果为空则直接执行sql
	 * @return 查询的数据库记录数
	 */
	Long count(String sql , MyBatisParamMap mybatisParamMap);
	
	/**
	 * 删除数据库记录
	 * 
	 * @param sql 该sql为mybatis类型的sql（参数占位符为#{}类型） 删除语句
	 * @param mybatisParamMap 参数 可以为空 如果为空则直接执行sql
	 * @return 删除记录的条数
	 */
	Integer delete(String sql , MyBatisParamMap mybatisParamMap);
	
	/**
	 * 修改数据库记录
	 * 
	 * @param sql 该sql为mybatis类型的sql（参数占位符为#{}类型） 修改语句
	 * @param mybatisParamMap 参数 可以为空 如果为空则直接执行sql
	 * @return 修改的记录数
	 */
	Integer update(String sql , MyBatisParamMap mybatisParamMap);
	
	/**
	 * 添加数据库记录
	 * 
	 * @param sql 该sql为mybatis类型的sql（参数占位符为#{}类型） 添加语句
	 * @param mybatisParamMap 参数 可以为空 如果为空则直接执行sql
	 * @return 添加的记录数
	 */
	Integer insert(String sql , MyBatisParamMap mybatisParamMap);
	
	/**
	 * @return SqlSession
	 */
	SqlSession getSqlSession();
	
}
