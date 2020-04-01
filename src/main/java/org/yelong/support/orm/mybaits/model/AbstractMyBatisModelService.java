/**
 * 
 */
package org.yelong.support.orm.mybaits.model;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.IterableUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.yelong.core.jdbc.BaseDataBaseOperation;
import org.yelong.core.jdbc.sql.BoundSql;
import org.yelong.core.jdbc.sql.executable.SelectSqlFragment;
import org.yelong.core.model.Model;
import org.yelong.core.model.ModelConfiguration;
import org.yelong.core.model.exception.ModelServiceException;
import org.yelong.core.model.resolve.ModelAndTable;
import org.yelong.core.model.service.AbstractSqlModelService;
import org.yelong.support.orm.mybaits.interceptor.ModelResultSetHandlerInteceptor;
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
 * 在1.0.2版本中弃用了 {@link ModelResultSetHandlerInteceptor} ，其功能完全由 {@link MappedStatementBuilder}来替换完成
 * 
 * @since 1.0.2 
 * @author PengFei
 */
@SuppressWarnings("deprecation")
public abstract class AbstractMyBatisModelService extends AbstractSqlModelService implements MyBatisModelService{

	private MappedStatementBuilder mappedStatementBuilder;

	@Deprecated
	protected boolean isCheckModelInteceptor = false;

	public AbstractMyBatisModelService(ModelConfiguration modelConfiguration) {
		super(modelConfiguration);
	}

	@Override
	public <M extends Model> List<M> execute(Class<M> modelClass,SelectSqlFragment selectSqlFragment) {
		if( selectSqlFragment.isPage() ) {
			throw new ModelServiceException("警告：目前不支持分页查询");
		}
		BoundSql boundSql = selectSqlFragment.getBoundSql();
		Map<String, Object> params = MyBatisMapperParamUtils.getMyBatisMapperParams(boundSql.getSql(), boundSql.getParams());
		//下面注释代码为1.0.1版本与 ModelResultSetHandlerInteceptor进行交互的代码。现在已经被弃用了

		//params.put("modelTable", getModelTable(modelClass));
		//params.put("modelAndTable", getModelAndTable(modelClass));

		//		ModelSelectMapper modelSelectMapper = getModelSelectMapper();
		//		if(!isCheckModelInteceptor) {
		//			checkModelInteceptor(getSqlSession());
		//			isCheckModelInteceptor = true;
		//		}
		//		return modelSelectMapper.select(params);
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
	public String getStatementId(Class<? extends Model> modelClass) {
		return modelClass.getName()+".Select";
	}
	
	@Override
	public SqlSession getSqlSession() {
		return getMyBatisBaseDataBaseOperation().getSqlSession();
	}

	/*
	 * 弃用的方法将在下一个版本进行删除
	 */

	/**
	 * @return
	 */
	@Deprecated
	protected ModelSelectMapper getModelSelectMapper() {
		SqlSession sqlSession = getMyBatisBaseDataBaseOperation().getSqlSession();
		try {
			ModelSelectMapper modelSelectMapper = sqlSession.getMapper(ModelSelectMapper.class);
			return modelSelectMapper;
		} catch (Exception e) {
			return addModelSelectMapper();
		}
	}

	/**
	 * @return
	 */
	@Deprecated
	protected synchronized ModelSelectMapper addModelSelectMapper() {
		SqlSession sqlSession = getMyBatisBaseDataBaseOperation().getSqlSession();
		ModelSelectMapper modelSelectMapper = null;
		try {
			modelSelectMapper = sqlSession.getMapper(ModelSelectMapper.class);
			return modelSelectMapper;
		} catch (Exception e) {
			sqlSession.getConfiguration().addMapper(ModelSelectMapper.class);
			modelSelectMapper = sqlSession.getMapper(ModelSelectMapper.class); 
		}
		return modelSelectMapper;
	}

	/**
	 * 检测 sqlSession 中是否存在model的拦截器
	 */
	@Deprecated
	protected synchronized void checkModelInteceptor(SqlSession sqlSession) {
		List<Interceptor> interceptors = sqlSession.getConfiguration().getInterceptors();
		//		if(!CollectionUtils.exists(interceptors, x->{
		//			return x instanceof ModelResultSetHandlerInteceptor;
		//		})) {
		//			sqlSession.getConfiguration().addInterceptor(new ModelResultSetHandlerInteceptor());
		//		}
		if(!IterableUtils.matchesAny(interceptors, x->{
			return x instanceof ModelResultSetHandlerInteceptor;
		})) {
			sqlSession.getConfiguration().addInterceptor(new ModelResultSetHandlerInteceptor());
		}
	}

}
