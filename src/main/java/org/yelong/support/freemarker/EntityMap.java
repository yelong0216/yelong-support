/**
 * 
 */
package org.yelong.support.freemarker;

import java.util.HashMap;
import java.util.Objects;

import org.yelong.support.ognl.OgnlWrapper;

import ognl.OgnlException;

/**
 * 实体映射<br/>
 * 
 * 用于存储实体对象<br/>
 * 
 * 可以设置属性获取时的默认值，防止freemarker解析错误问题
 */
public class EntityMap<T> extends HashMap<String, Object> {//必须继承 HashMap，且clone返回本身

	private static final long serialVersionUID = -1313237965432896603L;

	public static final String DEFAULT_VALUE = "";

	private T entity;

	private String defaultValue = DEFAULT_VALUE;

	private OgnlWrapper ognlWrapper = new OgnlWrapper();

	public EntityMap(T entity) {
		Objects.requireNonNull(entity);
		this.entity = entity;
		ognlWrapper.setRoot(entity);
	}

	public T getEntity() {
		return entity;
	}

	@Override
	public Object get(Object key) {
		Object value = super.get(key);
		if (null == value) {
			try {
				value = ognlWrapper.getValue(key.toString());
			} catch (OgnlException e) {

			}
		}
		return value != null ? value : defaultValue;
	}

	public EntityMap<T> setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}

	/**
	 * 如果设置的值与 entity 中属性冲突，已 put 值为准
	 */
	public Object put(String key, Object value) {
		return super.put(key, value);
	}

	@Override
	public Object clone() {
		// freemarker 会调用 HashMap 的 clone 方法。这里直接返回自己
		return this;
	}

}
