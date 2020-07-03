/**
 * 
 */
package org.yelong.support.spring.jdbc.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.yelong.support.orm.mybaits.mapper.AbstractMyBatisBaseDataBaseOperation;
import org.yelong.support.orm.mybaits.mapper.MyBatisBaseDataBaseOperation;
import org.yelong.support.orm.mybaits.sql.MyBatisParamMap;

/**
 * 存在事务的MyBatisBaseDataBaseOperation
 * 
 * 由于{@link Transactional}注解不适用于父类方法，所以在此抽象类中将{@link MyBatisBaseDataBaseOperation}包含及其父类中所有的方法实现默认实现，以实现事务
 * 
 * @author PengFei
 */
@Transactional
public abstract class TransactionalMyBatisBaseDataBaseOperation extends AbstractMyBatisBaseDataBaseOperation {

	@Override
	public List<Map<String, Object>> select(String sql, Object... params) {
		return super.select(sql, params);
	}

	@Override
	public Map<String, Object> selectRow(String sql, Object... params) {
		return super.selectRow(sql, params);
	}

	@Override
	public <T> List<T> selectColumn(String sql, Object... params) {
		return super.selectColumn(sql, params);
	}

	@Override
	public <T> T selectSingleObject(String sql, Object... params) {
		return super.selectSingleObject(sql, params);
	}

	@Override
	public Long count(String sql, Object... params) {
		return super.count(sql, params);
	}

	@Override
	public Integer delete(String sql, Object... params) {
		return super.delete(sql, params);
	}

	@Override
	public Integer update(String sql, Object... params) {
		return super.update(sql, params);
	}

	@Override
	public Integer insert(String sql, Object... params) {
		return super.insert(sql, params);
	}

	@Override
	public List<Map<String, Object>> select(String sql, MyBatisParamMap mybatisParamMap) {
		return super.select(sql, mybatisParamMap);
	}

	@Override
	public Map<String, Object> selectRow(String sql, MyBatisParamMap mybatisParamMap) {
		return super.selectRow(sql, mybatisParamMap);
	}

	@Override
	public <T> List<T> selectColumn(String sql, MyBatisParamMap mybatisParamMap) {
		return super.selectColumn(sql, mybatisParamMap);
	}

	@Override
	public <T> T selectSingleObject(String sql, MyBatisParamMap mybatisParamMap) {
		return super.selectSingleObject(sql, mybatisParamMap);
	}

	@Override
	public Long count(String sql, MyBatisParamMap mybatisParamMap) {
		return super.count(sql, mybatisParamMap);
	}

	@Override
	public Integer delete(String sql, MyBatisParamMap mybatisParamMap) {
		return super.delete(sql, mybatisParamMap);
	}

	@Override
	public Integer update(String sql, MyBatisParamMap mybatisParamMap) {
		return super.update(sql, mybatisParamMap);
	}

	@Override
	public Integer insert(String sql, MyBatisParamMap mybatisParamMap) {
		return super.insert(sql, mybatisParamMap);
	}

}
