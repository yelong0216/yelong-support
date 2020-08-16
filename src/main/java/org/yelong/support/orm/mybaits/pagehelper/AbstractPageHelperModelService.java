/**
 * 
 */
package org.yelong.support.orm.mybaits.pagehelper;

import org.yelong.core.jdbc.sql.executable.SelectSqlFragment;
import org.yelong.core.model.ModelConfiguration;
import org.yelong.support.orm.mybaits.model.AbstractMyBatisModelService;

import com.github.pagehelper.PageHelper;

/**
 * @since 1.1
 */
public abstract class AbstractPageHelperModelService extends AbstractMyBatisModelService {

	public AbstractPageHelperModelService(ModelConfiguration modelConfiguration) {
		super(modelConfiguration);
	}

	@Override
	protected SelectSqlFragment pageIntercept(SelectSqlFragment selectSqlFragment) {
		PageHelper.startPage(selectSqlFragment.getPageNum(), selectSqlFragment.getPageSize());
		return selectSqlFragment;
	}

}
