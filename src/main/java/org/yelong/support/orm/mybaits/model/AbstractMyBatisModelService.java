/**
 * 
 */
package org.yelong.support.orm.mybaits.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.yelong.core.jdbc.BaseDataBaseOperation;
import org.yelong.core.jdbc.sql.BoundSql;
import org.yelong.core.jdbc.sql.executable.SelectSqlFragment;
import org.yelong.core.model.ModelConfiguration;
import org.yelong.core.model.Modelable;
import org.yelong.core.model.exception.ModelServiceException;
import org.yelong.core.model.resolve.ModelAndTable;
import org.yelong.core.model.service.AbstractSqlModelService;
import org.yelong.support.orm.mybaits.model.mapping.MappedStatementBuilder;
import org.yelong.support.orm.mybaits.model.mapping.ResultMapBuilder;
import org.yelong.support.orm.mybaits.model.mapping.ResultMappingBuilder;
import org.yelong.support.orm.mybaits.model.mapping.defaults.DefaultMappedStatementBuilder;
import org.yelong.support.orm.mybaits.model.mapping.defaults.DefaultResultMapBuilder;
import org.yelong.support.orm.mybaits.model.mapping.defaults.DefaultResultMappingBuilder;
import org.yelong.support.orm.mybaits.util.MyBatisMapperParamUtils;

/**
 * MyBatis model service 实现
 * 
 * @since 1.0.2 
 * @author PengFei
 */
public abstract class AbstractMyBatisModelService extends AbstractSqlModelService implements MyBatisModelService{

	private MappedStatementBuilder mappedStatementBuilder;

	public AbstractMyBatisModelService(ModelConfiguration modelConfiguration) {
		super(modelConfiguration);
	}

	@Override
	public <M extends Modelable> List<M> execute(Class<M> modelClass,SelectSqlFragment selectSqlFragment) {
		if( selectSqlFragment.isPage() ) {
			throw new ModelServiceException("警告：目前不支持分页查询");
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

	@Override
	public final BaseDataBaseOperation getBaseDataBaseOperation() {
		return getMyBatisBaseDataBaseOperation();
	}

	/**
	 * @return MappedStatement Builder
	 */
	public MappedStatementBuilder getMappedStatementBuilder() {
		if( null == mappedStatementBuilder ) {
			ResultMappingBuilder resultMappingBuilder = new DefaultResultMappingBuilder();
			ResultMapBuilder resultMapBuilder = new DefaultResultMapBuilder(resultMappingBuilder);
			mappedStatementBuilder = new DefaultMappedStatementBuilder(resultMapBuilder);
		}
		return mappedStatementBuilder;
	}

	/**
	 * @param mappedStatementBuilder MappedStatement Builder
	 */
	public void setMappedStatementBuilder(MappedStatementBuilder mappedStatementBuilder) {
		this.mappedStatementBuilder = mappedStatementBuilder;
	}

	/**
	 * 默认的 sqlSource 这仅针对model的查询操作
	 * @param configuration myBatis configuration 
	 * @return default sqlSource
	 */
	public SqlSource defaultSelectModelSqlSource(Configuration configuration) {
		LanguageDriver languageDriver = configuration.getLanguageDriver(null);
		SqlSource sqlSource = languageDriver.createSqlSource(configuration, "${sql}", Map.class);
		return sqlSource;
	}

	/**
	 * Statement Id
	 * @param modelClass
	 * @return 
	 */
	public String getStatementId(Class<? extends Modelable> modelClass) {
		return modelClass.getName()+".Select";
	}
	
	@Override
	public SqlSession getSqlSession() {
		return getMyBatisBaseDataBaseOperation().getSqlSession();
	}

}
