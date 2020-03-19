/**
 * 
 */
package org.yelong.support.orm.mybaits.mapper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.yelong.support.orm.mybaits.sql.MyBatisParamMap;
import org.yelong.support.orm.mybaits.util.MyBatisMapperParamUtils;

/**
 * 基于mybatis的基础数据操作实现。
 * 这里的操作将通过mybatis来执行
 * @author 彭飞
 * @date 2019年8月14日上午11:40:32
 * @version 1.0
 */
public abstract class AbstractMyBatisBaseDataBaseOperation implements MyBatisBaseDataBaseOperation{


	public AbstractMyBatisBaseDataBaseOperation() {
		
	}

	@Override
	public List<Map<String, Object>> select(String sql, Object ... params) {
		return getMyBatisDBOperationMapper().select(convertMyBatisParams(sql, params));
	}

	@Override
	public Map<String, Object> selectRow(String sql, Object ... params) {
		List<Map<String, Object>> list = select(sql, params);
		if( list == null || list.isEmpty() ) {
			return Collections.emptyMap();
		}
		return list.get(0);
	}


	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> selectColumn(String sql, Object ... params) {
		return (List<T>) getMyBatisDBOperationMapper().selectByColumn(convertMyBatisParams(sql, params));
	}

	@Override
	public <T> T selectSingleObject(String sql, Object ... params) {
		List<T> list = selectColumn(sql, params);
		if( null == list || list.isEmpty() ) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public Long count(String sql, Object ... params) {
		Object result = selectSingleObject(sql, params);
		if( result instanceof BigDecimal ) {
			return ((BigDecimal)result).longValue();
		} 
		return (long)result;
	}

	@Override
	public Integer delete(String sql, Object ... params) {
		return getMyBatisDBOperationMapper().update(convertMyBatisParams(sql, params));
	}

	@Override
	public Integer update(String sql, Object ... params) {
		return getMyBatisDBOperationMapper().update(convertMyBatisParams(sql, params));
	}

	@Override
	public Integer insert(String sql, Object ... params) {
		return getMyBatisDBOperationMapper().update(convertMyBatisParams(sql, params));
	}

	/**
	 * 转换为mybatis的参数
	 * @author 彭飞
	 * @date 2019年7月26日下午6:30:22
	 * @version 1.0
	 * @param sql
	 * @param params
	 * @return
	 */
	protected Map<String,Object> convertMyBatisParams(String sql , Object [] params){
		return MyBatisMapperParamUtils.getMyBatisMapperParams(sql, params);
	}

	/**
	 * 获取sqlSession
	 * @author 彭飞
	 * @date 2019年8月14日上午11:41:28
	 * @version 1.0
	 * @return
	 */
	public abstract SqlSession getSqlSession();

	@Override
	public List<Map<String, Object>> select(String sql, MyBatisParamMap mybatisParamMap) {
		return getMyBatisDBOperationMapper().select(getParams(sql, mybatisParamMap));
	}

	@Override
	public Map<String, Object> selectRow(String sql, MyBatisParamMap mybatisParamMap) {
		List<Map<String, Object>> list = select(sql, mybatisParamMap);
		if( list == null || list.isEmpty() ) {
			return Collections.emptyMap();
		}
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> selectColumn(String sql, MyBatisParamMap mybatisParamMap) {
		return (List<T>) getMyBatisDBOperationMapper().selectByColumn(getParams(sql, mybatisParamMap));
	}

	@Override
	public <T> T selectSingleObject(String sql, MyBatisParamMap mybatisParamMap) {
		List<T> list = selectColumn(sql, mybatisParamMap);
		if( null == list || list.isEmpty() ) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public Long count(String sql, MyBatisParamMap mybatisParamMap) {
		Object result = selectSingleObject(sql, mybatisParamMap);
		if( result instanceof BigDecimal ) {
			return ((BigDecimal)result).longValue();
		} 
		return (long)result;
	}

	@Override
	public Integer delete(String sql, MyBatisParamMap mybatisParamMap) {
		return getMyBatisDBOperationMapper().update(getParams(sql, mybatisParamMap));
	}

	@Override
	public Integer update(String sql, MyBatisParamMap mybatisParamMap) {
		return getMyBatisDBOperationMapper().update(getParams(sql, mybatisParamMap));
	}

	@Override
	public Integer insert(String sql, MyBatisParamMap mybatisParamMap) {
		return getMyBatisDBOperationMapper().update(getParams(sql, mybatisParamMap));
	}

	
	protected MyBatisDBOperationMapper getMyBatisDBOperationMapper() {
		return addMyBatisDBOperationMapper();
	}
	
	private synchronized MyBatisDBOperationMapper addMyBatisDBOperationMapper() {
		try {
			return getSqlSession().getMapper(MyBatisDBOperationMapper.class);
		} catch (Exception e) {
			getSqlSession().getConfiguration().addMapper(MyBatisDBOperationMapper.class);
			return getSqlSession().getMapper(MyBatisDBOperationMapper.class);
		}
	}
	
	
	/**
	 * 
	 * @author 彭飞
	 * @date 2019年9月11日下午5:45:37
	 * @version 1.2
	 * @param sql
	 * @param mybatisParamMap
	 * @return
	 */
	private Map<String,Object> getParams(String sql , MyBatisParamMap mybatisParamMap){
		return MyBatisMapperParamUtils.getMyBatisMapperParams(sql, mybatisParamMap);
	}

	@Override
	public Connection getConnection() {
		try {
			return getSqlSession().getConfiguration().getEnvironment().getDataSource().getConnection();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
