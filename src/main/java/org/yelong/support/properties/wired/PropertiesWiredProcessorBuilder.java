/**
 * 
 */
package org.yelong.support.properties.wired;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.yelong.commons.util.PropertiesUtils;
import org.yelong.core.annotation.Nullable;

/**
 * 构建属性装配执行器
 * @author 彭飞
 * @date 2019年9月11日下午3:27:43
 * @version 1.2
 */
public class PropertiesWiredProcessorBuilder {

	
	/**
	 * 构建属性装配执行器
	 * objClass必须被{@link PropertiesWired}进行标注
	 * @author 彭飞
	 * @date 2019年9月11日下午3:34:27
	 * @version 1.2
	 * @param <T>
	 * @param objClass 被装配的属性类
	 * @return 属性装配执行器
	 * @throws InstantiationException objClass不支持实例化
	 * @throws UnsupportedOperationException objClass没有被{@link PropertiesWired}进行标注
	 */
	public static <T> PropertiesWiredProcessor<T> builder(Class<T> objClass) throws InstantiationException, IllegalAccessException {
		return builder(objClass.newInstance());
	}
	
	/**
	 * 构建属性装配执行器
	 * objClass必须被{@link PropertiesWired}进行标注
	 * @author 彭飞
	 * @date 2019年9月11日下午3:34:27
	 * @version 1.2
	 * @param <T>
	 * @param objClass 被装配的属性类
	 * @param properties 属性对象
	 * @return 属性装配执行器
	 * @throws InstantiationException objClass不支持实例化
	 * @throws UnsupportedOperationException objClass没有被{@link PropertiesWired}进行标注
	 */
	public static <T> PropertiesWiredProcessor<T> builder(Class<T> objClass,Properties properties) throws InstantiationException, IllegalAccessException {
		return builder(objClass.newInstance(),properties);
	}
	
	/**
	 * 构建属性装配执行器
	 * objClass必须被{@link PropertiesWired}进行标注
	 * @author 彭飞
	 * @date 2019年9月11日下午3:34:27
	 * @version 1.2
	 * @param <T>
	 * @param objClass 被装配的属性类
	 * @param properties  属性对象
	 * @param propertiesPrefix 属性前缀
	 * @return 属性装配执行器
	 * @throws InstantiationException objClass不支持实例化
	 * @throws IllegalAccessException objClass不支持实例化
	 * @throws UnsupportedOperationException objClass没有被{@link PropertiesWired}进行标注
	 */
	public static <T> PropertiesWiredProcessor<T> builder(Class<T> objClass,Properties properties,String propertiesPrefix) throws InstantiationException, IllegalAccessException {
		return builder(objClass.newInstance(),properties,propertiesPrefix);
	}
	
	
	/**
	 * 构建属性装配执行器
	 * objClass必须被{@link PropertiesWired}进行标注
	 * @author 彭飞
	 * @date 2019年9月11日下午3:34:27
	 * @version 1.2
	 * @param <T>
	 * @param obj 被装配的对象
	 * @return 属性装配执行器
	 * @throws UnsupportedOperationException objClass没有被{@link PropertiesWired}进行标注
	 */
	public static <T> PropertiesWiredProcessor<T> builder(T obj){
		Class<?> objClass = obj.getClass();
		if( !objClass.isAnnotationPresent(PropertiesWired.class)) {
			throw new UnsupportedOperationException("不支持属性装配！");
		}
		PropertiesWired pw = objClass.getAnnotation(PropertiesWired.class);
		String propertiesName = pw.propertiesName();
		if( StringUtils.isEmpty(propertiesName) ) {
			throw new NullPointerException("没有找到可以进行装配的Proeprties对象");
		}
		Properties properties = PropertiesUtils.load(propertiesName);
		if( null == properties ) {
			throw new NullPointerException("没有找到："+pw.propertiesName()+"的配置文件！");
		}
		return builder(obj, properties, pw.prefix());
	}
	
	/**
	 * 构建属性装配执行器
	 * @author 彭飞
	 * @date 2019年9月11日下午3:34:27
	 * @version 1.2
	 * @param <T>
	 * @param obj 被装配的对象
	 * @param properties 属性对象
	 * @return 属性装配执行器
	 */
	public static <T> PropertiesWiredProcessor<T> builder(T obj , Properties properties){
		String prefix = null;
		PropertiesWired pw = obj.getClass().getAnnotation(PropertiesWired.class);
		if( null != pw) {
			prefix = pw.prefix();
		}
		return builder(obj, properties,prefix);
	}
	
	/**
	 * 构建属性装配执行器
	 * @author 彭飞
	 * @date 2019年9月11日下午3:34:27
	 * @version 1.2
	 * @param <T>
	 * @param obj 被装配的对象
	 * @param properties 属性对象
	 * @param propertiesPrefix 属性前缀
	 * @return 属性装配执行器
	 */
	public static <T> PropertiesWiredProcessor<T> builder(T obj , Properties properties ,@Nullable String propertiesPrefix){
		return new PropertiesWiredProcessor<T>(obj,properties,propertiesPrefix);
	}
	
	
}
