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
import org.yelong.commons.util.ListUtilsE;
import org.yelong.support.orm.mybaits.sql.MyBatisParamMap;
import org.yelong.support.orm.mybaits.util.MyBatisMapperParamUtils;

/**
 * 基于mybatis的基础数据操作实现。<br/>
 * 
 * 这里的操作将通过mybatis来执行
 * 
 * @since 1.0
 */
public abstract class AbstractMyBatisBaseDataBaseOperation implements MyBatisBaseDataBaseOperation {

	public AbstractMyBatisBaseDataBaseOperation() {
	}

	@Override
	public List<Map<String, Object>> select(String sql, Object... params) {
		return getMyBatisDBOperationMapper().select(convertMyBatisParams(sql, params));
	}

	@Override
	public Map<String, Object> selectRow(String sql, Object... params) {
		return ListUtilsE.get(select(sql, params), 0, Collections.emptyMap());
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> selectColumn(String sql, Object... params) {
		return (List<T>) getMyBatisDBOperationMapper().selectByColumn(convertMyBatisParams(sql, params));
	}

	@Override
	public <T> T selectSingleObject(String sql, Object... params) {
		return ListUtilsE.get(selectColumn(sql, params), 0);
	}

	@Override
	public Long count(String sql, Object... params) {
		Object result = selectSingleObject(sql, params);
		if (result instanceof BigDecimal) {
			return ((BigDecimal) result).longValue();
		}
		return (long) result;
	}

	@Override
	public Integer delete(String sql, Object... params) {
		return getMyBatisDBOperationMapper().update(convertMyBatisParams(sql, params));
	}

	@Override
	public Integer update(String sql, Object... params) {
		return getMyBatisDBOperationMapper().update(convertMyBatisParams(sql, params));
	}

	@Override
	public Integer insert(String sql, Object... params) {
		return getMyBatisDBOperationMapper().update(convertMyBatisParams(sql, params));
	}

	/**
	 * 转换为mybatis的参数
	 */
	protected Map<String, Object> convertMyBatisParams(String sql, Object[] params) {
		return MyBatisMapperParamUtils.getMyBatisMapperParams(sql, params);
	}

	/**
	 * @return sqlSession
	 */
	public abstract SqlSession getSqlSession();

	@Override
	public List<Map<String, Object>> select(String sql, MyBatisParamMap mybatisParamMap) {
		return getMyBatisDBOperationMapper().select(getParams(sql, mybatisParamMap));
	}

	@Override
	public Map<String, Object> selectRow(String sql, MyBatisParamMap mybatisParamMap) {
		return ListUtilsE.get(select(sql, mybatisParamMap), 0, Collections.emptyMap());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> selectColumn(String sql, MyBatisParamMap mybatisParamMap) {
		return (List<T>) getMyBatisDBOperationMapper().selectByColumn(getParams(sql, mybatisParamMap));
	}

	@Override
	public <T> T selectSingleObject(String sql, MyBatisParamMap mybatisParamMap) {
		return ListUtilsE.get(selectColumn(sql, mybatisParamMap), 0);
	}

	@Override
	public Long count(String sql, MyBatisParamMap mybatisParamMap) {
		Object result = selectSingleObject(sql, mybatisParamMap);
		if (result instanceof BigDecimal) {
			return ((BigDecimal) result).longValue();
		}
		return (long) result;
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

	private Map<String, Object> getParams(String sql, MyBatisParamMap mybatisParamMap) {
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
