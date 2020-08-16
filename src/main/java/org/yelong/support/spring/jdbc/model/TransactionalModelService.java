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

	// ==================================================SqlFragmentExecutor==================================================

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

	// ==================================================ModelSqlFragmentExecutor==================================================

	// ==================================================ModelService==================================================

	@Override
	public boolean save(Modelable model) {
		return super.save(model);
	}

	@Override
	public boolean saveSelective(Modelable model) {
		return super.saveSelective(model);
	}

	@Override
	public Integer removeBySqlFragment(Class<? extends Modelable> modelClass,
			ConditionSqlFragment conditionSqlFragment) {
		return super.removeBySqlFragment(modelClass, conditionSqlFragment);
	}

	@Override
	public Integer modifyBySqlFragment(Modelable model, ConditionSqlFragment conditionSqlFragment) {
		return super.modifyBySqlFragment(model, conditionSqlFragment);
	}

	@Override
	public Integer modifySelectiveBySqlFragment(Modelable model, ConditionSqlFragment conditionSqlFragment) {
		return super.modifySelectiveBySqlFragment(model, conditionSqlFragment);
	}

	@Override
	public Long countBySqlFragment(Class<? extends Modelable> modelClass, ConditionSqlFragment conditionSqlFragment) {
		return super.countBySqlFragment(modelClass, conditionSqlFragment);
	}

	@Override
	public Long countBySqlFragment(String countSql, Object[] countSqlParams,
			ConditionSqlFragment conditionSqlFragment) {
		return super.countBySqlFragment(countSql, countSqlParams, conditionSqlFragment);
	}

	@Override
	public <M extends Modelable> List<M> findBySqlFragment(Class<M> modelClass,
			ConditionSqlFragment conditionSqlFragment, SortSqlFragment sortSqlFragment) {
		return super.findBySqlFragment(modelClass, conditionSqlFragment, sortSqlFragment);
	}

	@Override
	public <M extends Modelable> List<M> findBySqlFragment(Class<M> modelClass, String selectSql,
			Object[] selectSqlParams, ConditionSqlFragment conditionSqlFragment, SortSqlFragment sortSqlFragment) {
		return super.findBySqlFragment(modelClass, selectSql, selectSqlParams, conditionSqlFragment, sortSqlFragment);
	}

	@Override
	public <M extends Modelable> List<M> findBySQL(Class<M> modelClass, String selectSql, Object[] params) {
		return super.findBySQL(modelClass, selectSql, params);
	}

	@Override
	public <M extends Modelable> M findFirstBySqlFragment(Class<M> modelClass,
			ConditionSqlFragment conditionSqlFragment, SortSqlFragment sortSqlFragment) {
		return super.findFirstBySqlFragment(modelClass, conditionSqlFragment, sortSqlFragment);
	}

	@Override
	public <M extends Modelable> M findFirstBySqlFragment(Class<M> modelClass, String selectSql,
			Object[] selectSqlParams, ConditionSqlFragment conditionSqlFragment, SortSqlFragment sortSqlFragment) {
		// TODO Auto-generated method stub
		return super.findFirstBySqlFragment(modelClass, selectSql, selectSqlParams, conditionSqlFragment,
				sortSqlFragment);
	}

	@Override
	public <M extends Modelable> M findFirstBySQL(Class<M> modelClass, String sql, Object[] params) {
		return super.findFirstBySQL(modelClass, sql, params);
	}

	@Override
	public <T> List<T> findSingleColumnBySqlFragment(Class<? extends Modelable> modelClass, String selectColumn,
			ConditionSqlFragment conditionSqlFragment, SortSqlFragment sortSqlFragment) {
		return super.findSingleColumnBySqlFragment(modelClass, selectColumn, conditionSqlFragment, sortSqlFragment);
	}

	@Override
	public <T> List<T> findSingleColumnBySqlFragment(String selectSql, Object[] selectSqlParams,
			ConditionSqlFragment conditionSqlFragment, SortSqlFragment sortSqlFragment) {
		return super.findSingleColumnBySqlFragment(selectSql, selectSqlParams, conditionSqlFragment, sortSqlFragment);
	}

	@Override
	public <T> T findFirstSingleColumnBySqlFragment(Class<? extends Modelable> modelClass, String selectColumn,
			ConditionSqlFragment conditionSqlFragment, SortSqlFragment sortSqlFragment) {
		return super.findFirstSingleColumnBySqlFragment(modelClass, selectColumn, conditionSqlFragment,
				sortSqlFragment);
	}

	@Override
	public <T> T findFirstSingleColumnBySqlFragment(String selectSql, Object[] selectSqlParams,
			ConditionSqlFragment conditionSqlFragment, SortSqlFragment sortSqlFragment) {
		return super.findFirstSingleColumnBySqlFragment(selectSql, selectSqlParams, conditionSqlFragment,
				sortSqlFragment);
	}

	@Override
	public <M extends Modelable> List<M> findPageBySqlFragment(Class<M> modelClass,
			ConditionSqlFragment conditionSqlFragment, SortSqlFragment sortSqlFragment, int pageNum, int pageSize) {
		return super.findPageBySqlFragment(modelClass, conditionSqlFragment, sortSqlFragment, pageNum, pageSize);
	}

	@Override
	public <M extends Modelable> List<M> findPageBySqlFragment(Class<M> modelClass, String selectSql,
			Object[] selectSqlParams, ConditionSqlFragment conditionSqlFragment, SortSqlFragment sortSqlFragment,
			int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return super.findPageBySqlFragment(modelClass, selectSql, selectSqlParams, conditionSqlFragment,
				sortSqlFragment, pageNum, pageSize);
	}

	@Override
	public <M extends Modelable> List<M> findPageBySQL(Class<M> modelClass, String selectSql, Object[] params,
			int pageNum, int pageSize) {
		return super.findPageBySQL(modelClass, selectSql, params, pageNum, pageSize);
	}

	// ==================================================SqlModelService==================================================

	@Override
	public Integer removeBySqlModel(Class<? extends Modelable> modelClass, SqlModel<? extends Modelable> sqlModel) {
		return super.removeBySqlModel(modelClass, sqlModel);
	}

	@Override
	public Integer modifyBySqlModel(Modelable model, SqlModel<? extends Modelable> sqlModel) {
		return super.modifyBySqlModel(model, sqlModel);
	}

	@Override
	public Integer modifySelectiveBySqlModel(Modelable model, SqlModel<? extends Modelable> sqlModel) {
		return super.modifySelectiveBySqlModel(model, sqlModel);
	}

	@Override
	public Long countBySqlModel(Class<? extends Modelable> modelClass, SqlModel<? extends Modelable> sqlModel) {
		return super.countBySqlModel(modelClass, sqlModel);
	}

	@Override
	public Long countBySqlModel(String countSql, Object[] countSqlParams, SqlModel<? extends Modelable> sqlModel) {
		return super.countBySqlModel(countSql, countSqlParams, sqlModel);
	}

	@Override
	public <M extends Modelable> List<M> findBySqlModel(Class<M> modelClass, SqlModel<? extends Modelable> sqlModel) {
		return super.findBySqlModel(modelClass, sqlModel);
	}

	@Override
	public <M extends Modelable> List<M> findBySqlModel(Class<M> modelClass, String selectSql, Object[] selectSqlParams,
			SqlModel<? extends Modelable> sqlModel) {
		return super.findBySqlModel(modelClass, selectSql, selectSqlParams, sqlModel);
	}

	@Override
	public <M extends Modelable> M findFirstBySqlModel(Class<M> modelClass, SqlModel<? extends Modelable> sqlModel) {
		return super.findFirstBySqlModel(modelClass, sqlModel);
	}

	@Override
	public <M extends Modelable> M findFirstBySqlModel(Class<M> modelClass, String selectSql, Object[] selectSqlParams,
			SqlModel<? extends Modelable> sqlModel) {
		return super.findFirstBySqlModel(modelClass, selectSql, selectSqlParams, sqlModel);
	}

	@Override
	public <T> List<T> findSingleColumnBySqlModel(Class<? extends Modelable> modelClass, String selectColumn,
			SqlModel<? extends Modelable> sqlModel) {
		return super.findSingleColumnBySqlModel(modelClass, selectColumn, sqlModel);
	}

	@Override
	public <T> List<T> findSingleColumnBySqlModel(String selectSql, Object[] selectSqlParams,
			SqlModel<? extends Modelable> sqlModel) {
		return super.findSingleColumnBySqlModel(selectSql, selectSqlParams, sqlModel);
	}

	@Override
	public <T> T findFirstSingleColumnBySqlModel(Class<? extends Modelable> modelClass, String selectColumn,
			SqlModel<? extends Modelable> sqlModel) {
		return super.findFirstSingleColumnBySqlModel(modelClass, selectColumn, sqlModel);
	}

	@Override
	public <T> T findFirstSingleColumnBySqlModel(String selectSql, Object[] selectSqlParams,
			SqlModel<? extends Modelable> sqlModel) {
		return super.findFirstSingleColumnBySqlModel(selectSql, selectSqlParams, sqlModel);
	}

	@Override
	public <M extends Modelable> List<M> findPageBySqlModel(Class<M> modelClass, SqlModel<? extends Modelable> sqlModel,
			int pageNum, int pageSize) {
		return super.findPageBySqlModel(modelClass, sqlModel, pageNum, pageSize);
	}

	@Override
	public <M extends Modelable> List<M> findPageBySqlModel(Class<M> modelClass, String selectSql,
			Object[] selectSqlParams, SqlModel<? extends Modelable> sqlModel, int pageNum, int pageSize) {
		return super.findPageBySqlModel(modelClass, selectSql, selectSqlParams, sqlModel, pageNum, pageSize);
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
