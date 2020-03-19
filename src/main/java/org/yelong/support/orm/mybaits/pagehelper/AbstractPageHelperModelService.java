/**
 * 
 */
package org.yelong.support.orm.mybaits.pagehelper;

import java.util.List;
import java.util.Map;

import org.yelong.core.jdbc.sql.BoundSql;
import org.yelong.core.jdbc.sql.executable.SelectSqlFragment;
import org.yelong.core.model.Model;
import org.yelong.core.model.ModelConfiguration;
import org.yelong.support.orm.mybaits.model.AbstractExtendMyBatisModelService;
import org.yelong.support.orm.mybaits.model.ModelSelectMapper;
import org.yelong.support.orm.mybaits.util.MyBatisMapperParamUtils;

import com.github.pagehelper.PageHelper;

/**
 * @author 彭飞
 * @date 2019年9月30日下午4:42:10
 * @version 1.2
 */
public abstract class AbstractPageHelperModelService extends AbstractExtendMyBatisModelService{

	public AbstractPageHelperModelService(ModelConfiguration modelConfiguration) {
		super(modelConfiguration);
	}
	
	@Override
	public <M extends Model> List<M> execute(Class<M> modelClass, SelectSqlFragment selectSqlFragment) {
		if( selectSqlFragment.isPage() ) {
			PageHelper.startPage(selectSqlFragment.getPageNum(), selectSqlFragment.getPageSize());
		}
		ModelSelectMapper modelSelectMapper = getModelSelectMapper();
		BoundSql boundSql = selectSqlFragment.getBoundSql();
		Map<String, Object> params = MyBatisMapperParamUtils.getMyBatisMapperParams(boundSql.getSql(), boundSql.getParams());
		params.put("modelAndTable", getModelAndTable(modelClass));
		if(!isCheckModelInteceptor) {
			checkModelInteceptor(getSqlSession());
			isCheckModelInteceptor = true;
		}
		return modelSelectMapper.select(params);
	}

}
