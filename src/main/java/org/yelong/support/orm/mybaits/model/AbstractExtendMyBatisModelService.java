/**
 * 
 */
package org.yelong.support.orm.mybaits.model;

import org.yelong.core.jdbc.sql.condition.ConditionSqlFragment;
import org.yelong.core.model.ModelConfiguration;
import org.yelong.core.model.Modelable;

/**
 * 可以拓展的modelService
 * 
 * @author PengFei
 */
public abstract class AbstractExtendMyBatisModelService extends AbstractMyBatisModelService {

	public AbstractExtendMyBatisModelService(ModelConfiguration modelConfiguration) {
		super(modelConfiguration);
	}

	@Override
	public <M extends Modelable> Integer save(M model, boolean selective,
			ModelColumnValidateWay modelColumnValidateWay) {
		beforeSave(model, selective, modelColumnValidateWay);
		Integer result = super.save(model, selective, modelColumnValidateWay);
		afterSave(model, result, selective, modelColumnValidateWay);
		return result;
	}

	@Override
	public <M extends Modelable, C extends ConditionSqlFragment> Integer modify(M model, boolean selective,
			ModelColumnValidateWay modelColumnValidateWay, C conditionFragment) {
		beforeModify(model, selective, modelColumnValidateWay, conditionFragment);
		Integer result = super.modify(model, selective, modelColumnValidateWay, conditionFragment);
		afterModify(model, result, selective, modelColumnValidateWay, conditionFragment);
		return result;
	}

	protected <M extends Modelable> void beforeSave(M model, boolean selective,
			ModelColumnValidateWay modelColumnValidateWay) {

	}

	protected <M extends Modelable> void afterSave(M model, Integer result, boolean selective,
			ModelColumnValidateWay modelColumnValidateWay) {

	}

	protected <M extends Modelable, C extends ConditionSqlFragment> void beforeModify(M model, boolean selective,
			ModelColumnValidateWay modelColumnValidateWay, C conditionFragment) {

	}

	protected <M extends Modelable, C extends ConditionSqlFragment> void afterModify(M model, Integer result,
			boolean selective, ModelColumnValidateWay modelColumnValidateWay, C conditionFragment) {

	}

}
