/**
 * 
 */
package org.yelong.support.orm.mybaits.interceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.yelong.support.orm.mybaits.sql.MyBatisParamAliasable;
import org.yelong.support.orm.mybaits.sql.MyBatisPlaceholderMap;

/**
 * mybatis动态sql参数拦截器<br/>
 * 如果{@link MyBatisPlaceholderMap}参数被{@link Param}注解标注，则设置{@link MyBatisPlaceholderMap}的参数的别名为注解的值
 * <br/>
 * 如果需要使用到{@link Param}注解，则需要将该拦截器添加入mybatis中<br/>
 * 如果同pageHelper分页一起使用，则这个拦截器应该在分页拦截器之前执行（mybatis拦截器执行顺序：配置文件中越靠前执行顺序越靠后-如果存在多个拦截器，那么靠前的拦截器会被后面的拦截器进行代理，所以，越靠后的越先执行）<br/>
 * 
 * @author 彭飞
 * @date 2019年8月9日上午9:20:32
 * @version 1.0
 */
@Intercepts({
	@Signature(method = "query", type = Executor.class, args = {
			MappedStatement.class, Object.class, RowBounds.class,
			ResultHandler.class }),
	@Signature(method = "query", type = Executor.class, args = {
			MappedStatement.class, Object.class, RowBounds.class,
			ResultHandler.class ,CacheKey.class,BoundSql.class})
})
public class MyBatisPlaceholderMapInterceptor extends AbstractInterceptor{

	@SuppressWarnings("unchecked")
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		final Object [] args = invocation.getArgs();
		final MappedStatement mappedStatement = (MappedStatement) args[0];
		final Object parament = args[1];
		Method method = getMapperMethod(mappedStatement, parament);
		Parameter [] parameters = method.getParameters();
		for (int i = 0; i < parameters.length; i++) {
			//如果参数是mybatisDynamicSql且被Param注解标注则设置mybatisDynamicSql前缀
			if(MyBatisParamAliasable.class.isAssignableFrom(parameters[i].getType())) {
				if(parameters[i].isAnnotationPresent(Param.class)) {
					//获取@Param注解值，并设置MyBatisDynamicSql的别名
					Param param = parameters[i].getAnnotation(Param.class);
					String paramName = param.value();
					if( parament instanceof MyBatisParamAliasable ) {
						MyBatisParamAliasable mpa = (MyBatisParamAliasable) parament;
						mpa.setParamAlias(paramName);
					} else if ( parament instanceof Map) {
						Map<String,Object> paramentMap = (Map<String,Object>)parament;
						MyBatisParamAliasable mpa = (MyBatisParamAliasable) paramentMap.get(paramName);
						mpa.setParamAlias(paramName);
					} 
				}
			}
		}
		return invocation.proceed();
	}








}
