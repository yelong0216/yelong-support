/**
 * 
 */
package org.yelong.support.orm.mybaits.pagehelper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.yelong.core.jdbc.sql.BoundSql;
import org.yelong.core.jdbc.sql.executable.SelectSqlFragment;
import org.yelong.core.model.Model;
import org.yelong.core.model.ModelConfiguration;
import org.yelong.core.model.resolve.ModelAndTable;
import org.yelong.support.orm.mybaits.model.AbstractExtendMyBatisModelService;
import org.yelong.support.orm.mybaits.util.MyBatisMapperParamUtils;

import com.github.pagehelper.PageHelper;

/**
 * @since 1.0.2
 * @author PengFei
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
		BoundSql boundSql = selectSqlFragment.getBoundSql();
		Map<String, Object> params = MyBatisMapperParamUtils.getMyBatisMapperParams(boundSql.getSql(), boundSql.getParams());
		ModelAndTable modelAndTable = getModelAndTable(modelClass);
		SqlSession sqlSession = getSqlSession();
		Configuration configuration = sqlSession.getConfiguration();
		String statementId = getStatementId(modelClass);
		if(!configuration.hasStatement(statementId)) {
			synchronized (this) {
				if(!configuration.hasStatement(statementId)) {
					SqlSource sqlSource = defaultSelectModelSqlSource(configuration);
					MappedStatement mappedStatement = getMappedStatementBuilder().buildSelect(statementId, modelAndTable, sqlSource, configuration);
					configuration.addMappedStatement(mappedStatement);
				}
			}
		}
		return sqlSession.selectList(statementId, params);
	}
	
}
