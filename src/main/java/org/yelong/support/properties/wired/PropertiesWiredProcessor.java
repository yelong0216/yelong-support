/**
 * 
 */
package org.yelong.support.properties.wired;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.yelong.core.annotation.Nullable;
import org.yelong.support.ognl.OgnlWrapper;

import ognl.OgnlException;

/**
 * 属性装配处理器
 * @author 彭飞
 * @date 2019年9月11日上午11:16:12
 * @version 1.2
 */
public final class PropertiesWiredProcessor<T>{

	private final T obj;

	private final Properties properties;

	private final String propertyPrefix;

	private final OgnlWrapper ognlWrapper;

	/**
	 * @param obj 被注入属性的对象
	 * @param properties 属性对象
	 * @param propertiesPrefix 前缀 可以为空
	 */
	public PropertiesWiredProcessor(T obj , Properties properties,@Nullable String propertyPrefix) {
		this.propertyPrefix = propertyPrefix;
		this.properties = properties;
		this.obj = obj;
		ognlWrapper = new OgnlWrapper();
		ognlWrapper.put("properties", this.obj);
		ognlWrapper.setRoot(this.obj);
	}

	/**
	 * 获取装配之后的对象
	 * @author 彭飞
	 * @date 2019年9月11日上午11:23:04
	 * @version 1.2
	 * @return
	 */
	public T wiredObj() {
		//配置文件中所有的属性名称
		Set<String> propertiesNames = properties.stringPropertyNames();
		List<OnglExpressionAndPropertyName> onglExpressionAndPropertyNameList = null;
		if( null != propertyPrefix ) {
			//置换为ognl表达式与属性名称的映射
			onglExpressionAndPropertyNameList = propertiesNames.stream()
					//去除非此属性类的前缀的属性名称
					.filter(x->x.startsWith(propertyPrefix))
					.map(x-> new OnglExpressionAndPropertyName(x.substring(propertyPrefix.length()+1), x))
					.collect(Collectors.toList());
		} else {
			onglExpressionAndPropertyNameList = new ArrayList<>();
			for (String propertiesName : propertiesNames) {
				onglExpressionAndPropertyNameList.add(new OnglExpressionAndPropertyName(propertiesName, propertiesName));
			}
		}
		for (OnglExpressionAndPropertyName entry : onglExpressionAndPropertyNameList) {
			String value = properties.getProperty(entry.getPropertyName());
			if( null == value ) {
				continue;
			}
			try {
				ognlWrapper.setValue(entry.getOgnlExpression(), value);
			} catch (OgnlException e) {

			}
		}
		return obj;
	}

	public T getObj() {
		return obj;
	}
	
	public String getPropertyPrefix() {
		return propertyPrefix;
	}
	
	public Properties getProperties() {
		return properties;
	}
	
	public Class<?> getObjClas(){
		return obj.getClass();
	}
	
	/**
	 * ognl表达式与属性名称映射
	 * @author 彭飞
	 * @date 2019年9月11日下午2:26:30
	 * @version 1.2
	 */
	private static class OnglExpressionAndPropertyName{

		private final String ognlExpression;

		private final String propertyName;

		public OnglExpressionAndPropertyName(String ognlExpression, String propertyName) {
			this.ognlExpression = ognlExpression;
			this.propertyName = propertyName;
		}

		public String getOgnlExpression() {
			return ognlExpression;
		}

		public String getPropertyName() {
			return propertyName;
		}



	}




}
