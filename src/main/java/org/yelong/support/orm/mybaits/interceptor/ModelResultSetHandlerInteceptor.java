/**
 * 
 */
package org.yelong.support.orm.mybaits.interceptor;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.builder.ResultMapResolver;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultFlag;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.ibatis.type.UnknownTypeHandler;
import org.yelong.core.model.Model;
import org.yelong.core.model.resolve.ModelAndTable;
import org.yelong.support.orm.mybaits.model.ModelSelectMapper;

/**
 * model结果集映射的默认实现。
 * 如果model没有映射结果集，则默认配置结果集进行映射
 * 
 * @author 彭飞
 * @date 2019年9月29日下午3:24:54
 * @version 1.2
 */
@Intercepts({
	@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
public class ModelResultSetHandlerInteceptor extends AbstractInterceptor{

	private static final Map<String,List<ResultMap>> MODEL_MAPPED_RESULTMAP = new HashMap<>();

	private static final AtomicInteger NUMBER_COUNTER = new AtomicInteger(0);

	//private static final TypeAliasRegistry ALIAS_REGISTRY = new TypeAliasRegistry();

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		DefaultResultSetHandler handler = (DefaultResultSetHandler)invocation.getTarget();
		MappedStatement statement = (MappedStatement) FieldUtils.readDeclaredField(handler, "mappedStatement",true);
		String nameSpace = statement.getId().substring(0,statement.getId().lastIndexOf("."));
		//判断是否是ModelSelectMapper接口
		if( !nameSpace.equals(ModelSelectMapper.class.getName()) ) {
			return invocation.proceed();
		}
		//判断是否是查询
		if (statement.getSqlCommandType() != SqlCommandType.SELECT) {
			return invocation.proceed();
		}
		//pageHelper会使用ModelSelectMapper接口查询返回值为Long的查询。这查询将排除映射
		if( isPageHelperCountSelect(statement) ) {
			return invocation.proceed();
		}
		//ModelTable modelTable = getModelTable(handler);
		//List<ResultMap> resultMapList = getResultMap(modelTable, handler);
		ModelAndTable modelAndTable = getModelAndTable(handler);
		List<ResultMap> resultMapList = getResultMap(modelAndTable, handler);
		replaceResultMapValue(statement, resultMapList);
		return invocation.proceed();
	}

	/**
	 * 判断查询是否是PageHelper的分页count查询
	 * @author 彭飞
	 * @date 2019年10月22日下午1:27:17
	 * @version 1.2
	 * @param statement
	 * @return
	 */
	protected boolean isPageHelperCountSelect(MappedStatement statement) {
		if( statement.getId().endsWith("select_COUNT") ) {
			return true;
		}
		List<ResultMap> resultMaps = statement.getResultMaps();
		if( resultMaps.size() == 1 && resultMaps.get(0).getType().equals(Long.class) ) {
			return true;
		}
		return false;
	}

	/**
	 * 获取结果映射
	 * @author 彭飞
	 * @date 2019年10月21日下午2:19:50
	 * @version 1.2
	 * @param modelClass
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	protected List<ResultMap> getResultMap(ModelAndTable modelAndTable , DefaultResultSetHandler handler) throws Exception{
		Class<?> modelClass = modelAndTable.getModelClass();
		List<ResultMap> resultMapList = MODEL_MAPPED_RESULTMAP.get(modelClass.getName());
		if( null != resultMapList) {
			return resultMapList;
		}
		return builderResultMap(modelAndTable, handler);
	}

	/**
	 * 构建结果映射
	 * @author 彭飞
	 * @date 2019年10月21日下午2:19:57
	 * @version 1.2
	 * @param modelClass
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	protected synchronized List<ResultMap> builderResultMap(ModelAndTable modelAndTable,DefaultResultSetHandler handler) throws Exception{
		Class<?> modelClass = modelAndTable.getModelClass();
		List<ResultMap> resultMapList = MODEL_MAPPED_RESULTMAP.get(modelClass.getName());
		if( null != resultMapList) {
			return resultMapList;
		}
		resultMapList = new ArrayList<>();
		MappedStatement statement = (MappedStatement) FieldUtils.readDeclaredField(handler, "mappedStatement",true);
		Configuration configuration = (Configuration) FieldUtils.readDeclaredField(handler, "configuration",true);
		String resource = statement.getResource();
		MapperBuilderAssistant builderAssistant = new MapperBuilderAssistant(configuration, resource);
		builderAssistant.setCurrentNamespace(ModelSelectMapper.class.getName());
		//for (ResultMap resultMap : statement.getResultMaps()) {
		//判断是否是一个简单类型，或者映射映射的结果集
		//			if( !needAutoMapper(resultMap) ) {
		//				resultMapList.add(resultMap);
		//				continue;
		//			}
		ResultMap rebuildResultMap = rebuildResultMap(modelAndTable,builderAssistant);
		resultMapList.add(rebuildResultMap);
		//}
		MODEL_MAPPED_RESULTMAP.put(modelClass.getName(), resultMapList);
		return resultMapList;
	}
	
	/**
	 * 
	 * @author 彭飞
	 * @date 2019年9月30日上午9:59:27
	 * @version 1.2
	 * @param <M>
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked" })
	protected <M extends Model> Class<M> getModelClass(DefaultResultSetHandler handler) throws Exception{
		return (Class<M>) getModelAndTable(handler).getModelClass();
	}
	
	/**
	 * @date 2019年11月6日上午10:53:05
	 * @version 1.2
	 * @param <M>
	 * @param handler
	 * @return modelTable
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException 
	 */
	@SuppressWarnings({ "rawtypes" })
	protected <M extends Model> ModelAndTable getModelAndTable(DefaultResultSetHandler handler) throws NoSuchFieldException, IllegalAccessException {
		ParameterHandler parameterHandler = (ParameterHandler) FieldUtils.readDeclaredField(handler, "parameterHandler",true);
		Object parameterObject = parameterHandler.getParameterObject();
		ModelAndTable modelAndTable = (ModelAndTable) ((Map)parameterObject).get("modelAndTable");
		return modelAndTable;
	}

	/**
	 * 重新构建resultMap
	 * @param modelTable
	 * @param builderAssistant
	 * @return
	 */
	protected ResultMap rebuildResultMap(ModelAndTable modelAndTable, MapperBuilderAssistant builderAssistant) {
		Class<?> type = modelAndTable.getModelClass();
		List<ResultMapping> resultMappings = rebuildResultMapping(modelAndTable, builderAssistant);
		String id = getCustomIdentifier(type);
		ResultMapResolver resultMapResolver = new ResultMapResolver(builderAssistant, id, type, null, null, resultMappings, null);
		return resultMapResolver.resolve();
	}
	
	/**
	 * 重写构建结果映射
	 * @param modelAndTable
	 * @param builderAssistant
	 * @return
	 */
	protected List<ResultMapping> rebuildResultMapping( ModelAndTable modelAndTable ,  MapperBuilderAssistant builderAssistant ){
		List<ResultMapping> resultMappings = new ArrayList<>();
		modelAndTable.getFieldAndColumns().stream().filter(x->!x.isExtend())//过滤拓展字段
		.forEach(x->{
			List<ResultFlag> flags = new ArrayList<>();
			//String columnName = x.getColumn();
			String selectColumnName = x.getSelectColumn();
			String propertyName = x.getFieldName();
			String nestedResultMapId = null;
			if(x.isPrimaryKey()) {
				flags.add(ResultFlag.ID);
			}
			TypeHandler<?> typeHandler = resolveTypeHandler(x.getFieldType(),null,builderAssistant);
			JdbcType jdbcType = StringUtils.isEmpty(x.getJdbcType())? null : JdbcType.valueOf(x.getJdbcType());
			ResultMapping resultMapping = new ResultMapping.Builder(builderAssistant.getConfiguration(), propertyName,selectColumnName,x.getFieldType())
					.flags(flags)
					.jdbcType(jdbcType)
					.nestedResultMapId(nestedResultMapId)
					.typeHandler(typeHandler)
					.build();
			resultMappings.add(resultMapping);
		});
		return resultMappings;
	}

	/**
	 * 解析类型处理器
	 * @author 彭飞
	 * @date 2019年9月30日上午10:49:52
	 * @version 1.2
	 * @return
	 */
	protected TypeHandler<?> resolveTypeHandler(Class<?> javaType,Class<? extends TypeHandler<?>> typeHandlerType,MapperBuilderAssistant builderAssistant){
		if( null == typeHandlerType || typeHandlerType == UnknownTypeHandler.class) {
			return null;
		}
		TypeHandlerRegistry typeHandlerRegistry = builderAssistant.getConfiguration().getTypeHandlerRegistry();
		TypeHandler<?> handler = typeHandlerRegistry.getMappingTypeHandler(typeHandlerType);
		if( null == handler) {
			handler = typeHandlerRegistry.getInstance(javaType, typeHandlerType);
		}
		return handler;
	}

	/**
	 * 自定义标识符
	 * @author 彭飞
	 * @date 2019年9月30日上午10:34:29
	 * @version 1.2
	 * @param type
	 * @return
	 */
	protected String getCustomIdentifier(Class<?> type) {
		StringBuilder idBuilder = new StringBuilder();
		idBuilder.append("_[");
		idBuilder.append(type.getSimpleName());
		idBuilder.append("]_");
		idBuilder.append(NUMBER_COUNTER.getAndIncrement());
		return idBuilder.toString();
	}

	/**
	 * 设置结果集
	 * @author 彭飞
	 * @date 2019年9月30日上午10:58:12
	 * @version 1.2
	 * @param statement
	 * @param resultMaps
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws NoSuchFieldException 
	 */
	protected void replaceResultMapValue(MappedStatement statement,List<ResultMap> resultMaps) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		//ObjectUtils.setObjectPropertyValue(statement, "resultMaps", resultMaps);
		FieldUtils.writeDeclaredField(statement, "resultMaps", resultMaps,true);
	}

}
