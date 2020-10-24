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
import org.yelong.core.model.manage.ModelAndTable;
import org.yelong.support.orm.mybaits.model.mapping.MappedStatementBuilder;
import org.yelong.support.orm.mybaits.model.mapping.ResultMapBuilder;
import org.yelong.support.orm.mybaits.model.mapping.ResultMappingBuilder;
import org.yelong.support.orm.mybaits.model.mapping.defaults.DefaultMappedStatementBuilder;
import org.yelong.support.orm.mybaits.model.mapping.defaults.DefaultResultMapBuilder;
import org.yelong.support.orm.mybaits.model.mapping.defaults.DefaultResultMappingBuilder;
import org.yelong.support.orm.mybaits.util.MyBatisMapperParamUtils;
import org.yelong.support.spring.jdbc.model.TransactionalModelService;

/**
 * MyBatis model service 实现
 * 
 * @since 1.1
 * @author PengFei
 */
public abstract class AbstractMyBatisModelService extends TransactionalModelService implements MyBatisModelService {

	private MappedStatementBuilder mappedStatementBuilder;
	
	public static final String statementIdPrefix = "org.yelong.";

	public AbstractMyBatisModelService(ModelConfiguration modelConfiguration) {
		super(modelConfiguration);
	}

	@Override
	public <M extends Modelable> List<M> execute(Class<M> modelClass, SelectSqlFragment selectSqlFragment) {
		if (selectSqlFragment.isPage()) {
			selectSqlFragment = pageIntercept(selectSqlFragment);
		}
		BoundSql boundSql = selectSqlFragment.getBoundSql();
		Map<String, Object> params = MyBatisMapperParamUtils.getMyBatisMapperParams(boundSql.getSql(),
				boundSql.getParams());
		ModelAndTable modelAndTable = getModelAndTable(modelClass);
		SqlSession sqlSession = getSqlSession();
		Configuration configuration = sqlSession.getConfiguration();
		String statementId = getStatementId(modelClass);
		if (!configuration.hasStatement(statementId)) {
			synchronized (this) {
				if (!configuration.hasStatement(statementId)) {
					SqlSource sqlSource = defaultSelectModelSqlSource(configuration);
					MappedStatement mappedStatement = getMappedStatementBuilder().buildSelect(statementId,
							modelAndTable, sqlSource, configuration);
					configuration.addMappedStatement(mappedStatement);
				}
			}
		}
		return sqlSession.selectList(statementId, params);
	}

	/**
	 * 分页拦截。如果 {@link SelectSqlFragment}是一个分页查看的SQL从回调此方法实现分页。
	 * 
	 * @param selectSqlFragment 查询SQL片段
	 * @return 分页后的查询SQL片段
	 */
	protected abstract SelectSqlFragment pageIntercept(SelectSqlFragment selectSqlFragment);

	@Override
	public final BaseDataBaseOperation getBaseDataBaseOperation() {
		return getMyBatisBaseDataBaseOperation();
	}

	/**
	 * @return MappedStatement Builder
	 */
	public MappedStatementBuilder getMappedStatementBuilder() {
		if (null == mappedStatementBuilder) {
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
	 * 
	 * @param configuration myBatis configuration
	 * @return default sqlSource
	 */
	public SqlSource defaultSelectModelSqlSource(Configuration configuration) {
		LanguageDriver languageDriver = configuration.getLanguageDriver(null);
		SqlSource sqlSource = languageDriver.createSqlSource(configuration, "${sql}", Map.class);
		return sqlSource;
	}

	/**
	 * 获取生成的ID。这个ID为mybatis中获取SQL唯一的标识。且这个标识也应用到了日志中。
	 * 
	 * @param modelClass 模型类型
	 * @return statementId
	 */
	public String getStatementId(Class<? extends Modelable> modelClass) {
		// 以org.yelong开头在日志设置mapper包目录时设置为 org.yelong 即可
		return statementIdPrefix + modelClass.getName() + ".select";
	}

	@Override
	public SqlSession getSqlSession() {
		return getMyBatisBaseDataBaseOperation().getSqlSession();
	}

}
