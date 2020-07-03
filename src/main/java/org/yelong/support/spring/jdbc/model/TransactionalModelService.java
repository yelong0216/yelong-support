/**
 * 
 */
package org.yelong.support.spring.jdbc.model;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.yelong.core.jdbc.sql.condition.ConditionSqlFragment;
import org.yelong.core.jdbc.sql.executable.CountSqlFragment;
import org.yelong.core.jdbc.sql.executable.DeleteSqlFragment;
import org.yelong.core.jdbc.sql.executable.InsertSqlFragment;
import org.yelong.core.jdbc.sql.executable.SelectSqlFragment;
import org.yelong.core.jdbc.sql.executable.SqlFragmentExecutable;
import org.yelong.core.jdbc.sql.executable.UpdateSqlFragment;
import org.yelong.core.jdbc.sql.sort.SortSqlFragment;
import org.yelong.core.model.ModelConfiguration;
import org.yelong.core.model.Modelable;
import org.yelong.core.model.collector.ModelCollector;
import org.yelong.core.model.service.AbstractSqlModelService;
import org.yelong.core.model.service.SqlModelService;
import org.yelong.core.model.service.function.MSConsumer;
import org.yelong.core.model.service.function.MSFunction;
import org.yelong.core.model.service.function.MSOperation;
import org.yelong.core.model.service.function.MSSupplier;
import org.yelong.core.model.sql.SqlModel;

/**
 * 存在事务的modelService
 * 
 * 由于{@link Transactional}注解不适用于父类方法，所以在此抽象类中将{@link SqlModelService}包含及其父类中所有的方法实现默认实现，以实现事务
 * 
 * @author PengFei
 */
@Transactional
public abstract class TransactionalModelService extends AbstractSqlModelService {

	public TransactionalModelService(ModelConfiguration modelConfiguration) {
		super(modelConfiguration);
	}

	@Override
	public <R> R execute(SqlFragmentExecutable sqlFragment) {
		return super.execute(sqlFragment);
	}

	@Override
	public Integer execute(InsertSqlFragment insertSqlFragment) {
		return super.execute(insertSqlFragment);
	}

	@Override
	public Integer execute(DeleteSqlFragment deleteSqlFragment) {
		return super.execute(deleteSqlFragment);
	}

	@Override
	public Integer execute(UpdateSqlFragment updateSqlFragment) {
		return super.execute(updateSqlFragment);
	}

	@Override
	public Long execute(CountSqlFragment countSqlFragment) {
		return super.execute(countSqlFragment);
	}

	@Override
	public List<Map<String, Object>> execute(SelectSqlFragment selectSqlFragment) {
		return super.execute(selectSqlFragment);
	}

	@Override
	public <M extends Modelable> boolean save(M model) {
		return super.save(model);
	}

	@Override
	public <M extends Modelable> boolean saveSelective(M model) {
		return super.saveSelective(model);
	}

	@Override
	public <M extends Modelable> Integer removeAll(Class<M> modelClass) {
		return super.removeAll(modelClass);
	}

	@Override
	public <M extends Modelable> boolean removeById(Class<M> modelClass, Object id) {
		return super.removeById(modelClass, id);
	}

	@Override
	public <M extends Modelable> Integer removeByIds(Class<M> modelClass, Object[] ids) {
		return super.removeByIds(modelClass, ids);
	}

	@Override
	public <M extends Modelable> boolean removeByOnlyPrimaryKey(Class<M> modelClass, Object primaryKeyValue) {
		return super.removeByOnlyPrimaryKey(modelClass, primaryKeyValue);
	}

	@Override
	public <M extends Modelable> Integer removeByOnlyPrimaryKey(Class<M> modelClass, Object[] primaryKeyValues) {
		return super.removeByOnlyPrimaryKey(modelClass, primaryKeyValues);
	}

	@Override
	public <M extends Modelable> Integer removeByCondition(Class<M> modelClass, ConditionSqlFragment condition) {
		return super.removeByCondition(modelClass, condition);
	}

	@Override
	public <M extends Modelable> boolean modifyById(M model) {
		return super.modifyById(model);
	}

	@Override
	public <M extends Modelable> boolean modifyByOnlyPrimaryKey(M model) {
		return super.modifyByOnlyPrimaryKey(model);
	}

	@Override
	public <M extends Modelable> boolean modifySelectiveById(M model) {
		return super.modifySelectiveById(model);
	}

	@Override
	public <M extends Modelable> boolean modifySelectiveByOnlyPrimaryKey(M model) {
		return super.modifySelectiveByOnlyPrimaryKey(model);
	}

	@Override
	public <M extends Modelable> Integer modifyByCondition(M model, ConditionSqlFragment conditionFragment) {
		return super.modifyByCondition(model, conditionFragment);
	}

	@Override
	public <M extends Modelable> Integer modifySelectiveByCondition(M model, ConditionSqlFragment conditionFragment) {
		return super.modifySelectiveByCondition(model, conditionFragment);
	}

	@Override
	public <M extends Modelable> Long countAll(Class<M> modelClass) {
		return super.countAll(modelClass);
	}

	@Override
	public <M extends Modelable> Long countById(Class<M> modelClass, Object id) {
		return super.countById(modelClass, id);
	}

	@Override
	public <M extends Modelable> Long countByOnlyPrimaryKey(Class<M> modelClass, Object primaryKeyValue) {
		return super.countByOnlyPrimaryKey(modelClass, primaryKeyValue);
	}

	@Override
	public <M extends Modelable> Long countByIds(Class<M> modelClass, Object[] ids) {
		return super.countByIds(modelClass, ids);
	}

	@Override
	public <M extends Modelable> Long countByOnlyPrimaryKey(Class<M> modelClass, Object[] primaryKeyValues) {
		return super.countByOnlyPrimaryKey(modelClass, primaryKeyValues);
	}

	@Override
	public <M extends Modelable> Long countByCondition(Class<M> modelClass, ConditionSqlFragment conditionFragment) {
		return super.countByCondition(modelClass, conditionFragment);
	}

	@Override
	public <M extends Modelable> boolean existById(Class<M> modelClass, Object id) {
		return super.existById(modelClass, id);
	}

	@Override
	public <M extends Modelable> boolean existByOnlyPrimaryKey(Class<M> modelClass, Object primaryKeyValue) {
		return super.existByOnlyPrimaryKey(modelClass, primaryKeyValue);
	}

	@Override
	public <M extends Modelable> boolean existByIds(Class<M> modelClass, Object[] ids) {
		return super.existByIds(modelClass, ids);
	}

	@Override
	public <M extends Modelable> boolean existByOnlyPrimaryKey(Class<M> modelClass, Object[] primaryKeyValues) {
		return super.existByOnlyPrimaryKey(modelClass, primaryKeyValues);
	}

	@Override
	public <M extends Modelable> boolean existByCondition(Class<M> modelClass, ConditionSqlFragment condition) {
		return super.existByCondition(modelClass, condition);
	}

	@Override
	public <M extends Modelable> List<M> findAll(Class<M> modelClass) {
		return super.findAll(modelClass);
	}

	@Override
	public <M extends Modelable> M findById(Class<M> modelClass, Object id) {
		return super.findById(modelClass, id);
	}

	@Override
	public <M extends Modelable> M findByOnlyPrimaryKey(Class<M> modelClass, Object primaryKeyValue) {
		return super.findByOnlyPrimaryKey(modelClass, primaryKeyValue);
	}

	@Override
	public <M extends Modelable> List<M> findByCondition(Class<M> modelClass, ConditionSqlFragment condition) {
		return super.findByCondition(modelClass, condition);
	}

	@Override
	public <M extends Modelable> M findFirstByCondition(Class<M> modelClass, ConditionSqlFragment condition) {
		return super.findFirstByCondition(modelClass, condition);
	}

	@Override
	public <M extends Modelable> List<M> findBySort(Class<M> modelClass, SortSqlFragment sort) {
		return super.findBySort(modelClass, sort);
	}

	@Override
	public <M extends Modelable> List<M> findByConditionSort(Class<M> modelClass, ConditionSqlFragment condition,
			SortSqlFragment sort) {
		return super.findByConditionSort(modelClass, condition, sort);
	}

	@Override
	public <M extends Modelable> List<M> findPage(Class<M> modelClass, Integer pageNum, Integer pageSize) {
		return super.findPage(modelClass, pageNum, pageSize);
	}

	@Override
	public <M extends Modelable> List<M> findPageByCondition(Class<M> modelClass, ConditionSqlFragment condition,
			Integer pageNum, Integer pageSize) {
		return super.findPageByCondition(modelClass, condition, pageNum, pageSize);
	}

	@Override
	public <M extends Modelable> List<M> findPageBySort(Class<M> modelClass, SortSqlFragment sort, Integer pageNum,
			Integer pageSize) {
		return super.findPageBySort(modelClass, sort, pageNum, pageSize);
	}

	@Override
	public <M extends Modelable> List<M> findPageByConditionSort(Class<M> modelClass, ConditionSqlFragment condition,
			SortSqlFragment sort, Integer pageNum, Integer pageSize) {
		return super.findPageByConditionSort(modelClass, condition, sort, pageNum, pageSize);
	}

	@Override
	public <M extends Modelable> List<M> findBySQL(Class<M> modelClass, String sql, Object[] params) {
		return super.findBySQL(modelClass, sql, params);
	}

	@Override
	public <M extends Modelable> M findFirstBySQL(Class<M> modelClass, String sql, Object[] params) {
		return super.findFirstBySQL(modelClass, sql, params);
	}

	@Override
	public <M extends Modelable> List<M> findPageBySQL(Class<M> modelClass, String sql, Object[] params,
			Integer pageNum, Integer pageSize) {
		return super.findPageBySQL(modelClass, sql, params, pageNum, pageSize);
	}

	@Override
	public ModelConfiguration getModelConfiguration() {
		return super.getModelConfiguration();
	}

	@Override
	public <M extends Modelable, S extends SqlModel> Integer removeBySqlModel(Class<M> modelClass, S sqlModel) {
		return super.removeBySqlModel(modelClass, sqlModel);
	}

	@Override
	public <M extends Modelable, S extends SqlModel> Integer removeBySqlModel(String sql, S sqlModel) {
		return super.removeBySqlModel(sql, sqlModel);
	}

	@Override
	public <M extends Modelable, S extends SqlModel> Integer modifyBySqlModel(M model, S sqlModel) {
		return super.modifyBySqlModel(model, sqlModel);
	}

	@Override
	public <M extends Modelable, S extends SqlModel> Integer modifySelectiveBySqlModel(M model, S sqlModel) {
		return super.modifySelectiveBySqlModel(model, sqlModel);
	}

	@Override
	public <M extends Modelable, S extends SqlModel> Integer modifyBySqlModel(String sql, Object[] params, S sqlModel) {
		return super.modifyBySqlModel(sql, params, sqlModel);
	}

	@Override
	public <M extends Modelable, S extends SqlModel> Long countBySqlModel(Class<M> modelClass, S sqlModel) {
		return super.countBySqlModel(modelClass, sqlModel);
	}

	@Override
	public <M extends Modelable, S extends SqlModel> boolean existBySqlModel(Class<M> modelClass, S sqlModel) {
		return super.existBySqlModel(modelClass, sqlModel);
	}

	@Override
	public <M extends Modelable, S extends SqlModel> Long countBySqlModel(String sql, S sqlModel) {
		return super.countBySqlModel(sql, sqlModel);
	}

	@Override
	public <M extends Modelable, S extends SqlModel> boolean existBySqlModel(String sql, S sqlModel) {
		return super.existBySqlModel(sql, sqlModel);
	}

	@Override
	public <M extends Modelable, S extends SqlModel> List<M> findBySqlModel(Class<M> modelClass, S sqlModel) {
		return super.findBySqlModel(modelClass, sqlModel);
	}

	@Override
	public <M extends Modelable, S extends SqlModel> M findFirstBySqlModel(Class<M> modelClass, S sqlModel) {
		return super.findFirstBySqlModel(modelClass, sqlModel);
	}

	@Override
	public <M extends Modelable, S extends SqlModel> List<M> findBySqlModel(Class<M> modelClass, String sql,
			S sqlModel) {
		return super.findBySqlModel(modelClass, sql, sqlModel);
	}

	@Override
	public <M extends Modelable, S extends SqlModel> M findFirstBySqlModel(Class<M> modelClass, String sql,
			S sqlModel) {
		return super.findFirstBySqlModel(modelClass, sql, sqlModel);
	}

	@Override
	public <M extends Modelable, S extends SqlModel> List<M> findPageBySqlModel(Class<M> modelClass, S sqlModel,
			int pageNum, int pageSize) {
		return super.findPageBySqlModel(modelClass, sqlModel, pageNum, pageSize);
	}

	@Override
	public <M extends Modelable, S extends SqlModel> List<M> findPageBySqlModel(Class<M> modelClass, String sql,
			S sqlModel, int pageNum, int pageSize) {
		return super.findPageBySqlModel(modelClass, sql, sqlModel, pageNum, pageSize);
	}

	@Override
	public <M extends Modelable, T> List<T> findSingleColumn(Class<M> modelClass, String selectColumn,
			ConditionSqlFragment condition, SortSqlFragment sort) {
		return super.findSingleColumn(modelClass, selectColumn, condition, sort);
	}

	@Override
	public <M extends Modelable, T> List<T> findSingleColumnByOnlyPrimaryKey(Class<M> modelClass, String selectColumn,
			Object primaryKeyValue) {
		return super.findSingleColumnByOnlyPrimaryKey(modelClass, selectColumn, primaryKeyValue);
	}

	@Override
	public <M extends Modelable, T> T findFirstSingleColumn(Class<M> modelClass, String selectColumn,
			ConditionSqlFragment condition, SortSqlFragment sort) {
		return super.findFirstSingleColumn(modelClass, selectColumn, condition, sort);
	}

	@Override
	public <M extends Modelable, T> T findFirstSingleColumnByOnlyPrimaryKey(Class<M> modelClass, String selectColumn,
			Object primaryKeyValue) {
		return super.findFirstSingleColumnByOnlyPrimaryKey(modelClass, selectColumn, primaryKeyValue);
	}

	@Override
	public <M extends Modelable, S extends SqlModel, T> T findFirstSingleColumnBySqlModel(Class<M> modelClass,
			String selectColumn, S sqlModel) {
		return super.findFirstSingleColumnBySqlModel(modelClass, selectColumn, sqlModel);
	}

	@Override
	public <M extends Modelable, S extends SqlModel, T> List<T> findSingleColumnBySqlModel(Class<M> modelClass,
			String selectColumn, S sqlModel) {
		return super.findSingleColumnBySqlModel(modelClass, selectColumn, sqlModel);
	}

	@Override
	public <M extends Modelable, R, T extends ModelCollector<M, R, T>> R collect(
			ModelCollector<M, R, T> modelCollector) {
		return super.collect(modelCollector);
	}

	@Override
	public <R> R doFunction(MSFunction<R> function) {
		return super.doFunction(function);
	}

	@Override
	public void doConsumer(MSConsumer consumer) {
		super.doConsumer(consumer);
	}

	@Override
	public void doOperation(MSOperation operation) {
		super.doOperation(operation);
	}

	@Override
	public <R> R doSupplier(MSSupplier<R> supplier) {
		return super.doSupplier(supplier);
	}

}
