/**
 * 
 */
package org.yelong.support.orm.mybaits.interceptor;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.InterceptorChain;
import org.apache.ibatis.session.Configuration;

/**
 * mybatis拦截器排序
 * @author 彭飞
 * @date 2019年8月14日上午11:21:49
 * @version 1.0
 */
public class MyBatisInterceptorSortUtils {
	
	/**
	 * mybatis拦截器排序
	 * @author 彭飞
	 * @date 2019年8月14日上午11:22:47
	 * @version 1.0
	 * @param configuration mybaits configuration
	 * @param c 排序
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void sortInterceptor(Configuration configuration,Comparator<? super Interceptor> c) throws Exception{
		//通过反射获取拦截器链
		Class<?> configurationClass = configuration.getClass();
		Field interceptorChainField = configurationClass.getDeclaredField("interceptorChain");
		interceptorChainField.setAccessible(true);
		InterceptorChain interceptorChain = (InterceptorChain) interceptorChainField.get(configuration);
		//在通过反射在链中获取拦截器集合
		Class<?> interceptorChainClass = interceptorChain.getClass();
		Field interceptorsField = interceptorChainClass.getDeclaredField("interceptors");
		interceptorsField.setAccessible(true);
		List<Interceptor> interceptors = (List<Interceptor>) interceptorsField.get(interceptorChain);
		interceptors.sort(c);
	}

}
