/**
 * 
 */
package org.yelong.support.orm.mybaits.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Plugin;

/**
 * @author 彭飞
 * @date 2019年9月17日上午8:50:56
 * @version 1.2
 */
public abstract class AbstractInterceptor implements Interceptor{
	
	/**
	 * 获取MappedStatement对应的接口中的方法，且这个方法不应被重载，如果对应的方法出现重载，则会抛出异常
	 * @author 彭飞
	 * @date 2019年9月17日上午10:16:30
	 * @version 1.2
	 * @param mappedStatement
	 * @param param 参数
	 * @return 对应的几口方法。如果没有此方法则返回null
	 * @throws Exception 如果这个方法被重载了
	 */
	public Method getMapperMethod(MappedStatement mappedStatement , Object param) throws Exception {
		//当前执行的完全命名空间
		String namespace = mappedStatement.getId();
		//映射的接口
		String className = namespace.substring(0 , namespace.lastIndexOf("."));
		//映射的执行的方法名称
		String methodName = namespace.substring(namespace.lastIndexOf(".")+1);
		Method [] methods = Class.forName(className).getMethods();
		List<Method> methodList = new ArrayList<Method>();
		for (Method method : methods) {
			//获取执行的方法{Method}
			if( methodName.equals(method.getName())) {
				methodList.add(method);
			}
		}
		if( methodList.isEmpty() ) {
			return null;
		} else if( methodList.size() > 1 ) {
			throw new Exception("获取映射器方法时，映射的mapper对象中的方法不应该重载。且mapper中的方法重载是不符合逻辑的，这会导致与xml的映射出现问题。");
		} else {
			return methodList.get(0);
		}
	}
	
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

	
	

}
