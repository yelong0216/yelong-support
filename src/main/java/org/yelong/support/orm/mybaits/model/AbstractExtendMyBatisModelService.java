/**
 * 
 */
package org.yelong.support.orm.mybaits.model;

import org.yelong.core.jdbc.sql.condition.ConditionSqlFragment;
import org.yelong.core.model.Model;
import org.yelong.core.model.ModelConfiguration;

/**
 * 可以拓展的modelService
 * @author PengFei
 */
public abstract class AbstractExtendMyBatisModelService extends AbstractMyBatisModelService{

	public AbstractExtendMyBatisModelService(ModelConfiguration modelConfiguration) {
		super(modelConfiguration);
	}

	@Override
	public <M extends Model> Integer save(M model,boolean selective , 
			ModelColumnValidateWay modelColumnValidateWay) {
		beforeSave(model,selective,modelColumnValidateWay);
		Integer result = super.save(model,selective, modelColumnValidateWay);
		afterSave(model, result,selective, modelColumnValidateWay);
		return result;
	}

	@Override
	public <M extends Model, C extends ConditionSqlFragment> Integer modify(M model,
			boolean selective , ModelColumnValidateWay modelColumnValidateWay, C conditionFragment) {
		beforeModify(model, selective , modelColumnValidateWay, conditionFragment);
		Integer result =  super.modify(model, selective,modelColumnValidateWay, conditionFragment);
		afterModify(model, result, selective , modelColumnValidateWay, conditionFragment);
		return result;
	}

	/**
	 * 在保存之前
	 * @param <M>
	 * @param model 
	 * @return 
	 */
	protected <M extends Model> void beforeSave(M model, boolean selective , 
			ModelColumnValidateWay modelColumnValidateWay) {

	}

	/**
	 * 保存之后
	 * @param <M>
	 * @param model 
	 * @param result 保存结果
	 */
	protected <M extends Model> void afterSave(M model, Integer result, boolean selective , 
			ModelColumnValidateWay modelColumnValidateWay) {

	}

	/**
	 * 修改之前
	 * @param <M>
	 * @param <C>
	 * @param model
	 * @param selective
	 * @param modelColumnValidateWay
	 * @param conditionFragment
	 */
	protected <M extends Model, C extends ConditionSqlFragment> void beforeModify(M model,boolean selective , 
			ModelColumnValidateWay modelColumnValidateWay, C conditionFragment) {

	}

	/**
	 * 修改之后
	 * @param <M>
	 * @param <C>
	 * @param model
	 * @param result
	 * @param selective
	 * @param modelColumnValidateWay
	 * @param conditionFragment
	 */
	protected <M extends Model,C extends ConditionSqlFragment>  void afterModify(M model, Integer result,
			boolean selective , ModelColumnValidateWay modelColumnValidateWay, C conditionFragment) {

	}

}
