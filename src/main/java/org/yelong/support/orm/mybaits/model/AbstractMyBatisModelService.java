/**
 * 
 */
package org.yelong.support.orm.mybaits.model;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.IterableUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.yelong.core.jdbc.BaseDataBaseOperation;
import org.yelong.core.jdbc.sql.BoundSql;
import org.yelong.core.jdbc.sql.executable.SelectSqlFragment;
import org.yelong.core.model.Model;
import org.yelong.core.model.ModelConfiguration;
import org.yelong.core.model.exception.ModelServiceException;
import org.yelong.core.model.service.AbstractSqlModelService;
import org.yelong.support.orm.mybaits.interceptor.ModelResultSetHandlerInteceptor;
import org.yelong.support.orm.mybaits.util.MyBatisMapperParamUtils;

/**
 * mybatis model service 实现
 * @author 彭飞
 * @date 2019年9月16日下午4:37:12
 * @version 1.2
 */
public abstract class AbstractMyBatisModelService extends AbstractSqlModelService implements MyBatisModelService{

	protected boolean isCheckModelInteceptor = false;

	public AbstractMyBatisModelService(ModelConfiguration modelConfiguration) {
		super(modelConfiguration);
	}

	@Override
	public <M extends Model> List<M> execute(Class<M> modelClass,SelectSqlFragment selectSqlFragment) {
		if( selectSqlFragment.isPage() ) {
			ModelServiceException modelServiceException = new ModelServiceException("警告：目前不支持分页查询");
			modelServiceException.printStackTrace();
		}
		ModelSelectMapper modelSelectMapper = getModelSelectMapper();
		BoundSql boundSql = selectSqlFragment.getBoundSql();
		Map<String, Object> params = MyBatisMapperParamUtils.getMyBatisMapperParams(boundSql.getSql(), boundSql.getParams());
		//params.put("modelTable", getModelTable(modelClass));
		params.put("modelAndTable", getModelAndTable(modelClass));
		if(!isCheckModelInteceptor) {
			checkModelInteceptor(getSqlSession());
			isCheckModelInteceptor = true;
		}
		return modelSelectMapper.select(params);
	}

	@Override
	public final BaseDataBaseOperation getBaseDataBaseOperation() {
		return getMyBatisBaseDataBaseOperation();
	}

	/**
	 * @author 彭飞
	 * @date 2019年9月30日上午11:25:07
	 * @version 1.2
	 * @return
	 */
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
	 * @author 彭飞
	 * @date 2019年9月30日上午11:25:07
	 * @version 1.2
	 * @return
	 */
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

	@Override
	public SqlSession getSqlSession() {
		return getMyBatisBaseDataBaseOperation().getSqlSession();
	}

	/**
	 * 检测 sqlSession 中是否存在model的拦截器
	 */
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
