/**
 * 
 */
package org.yelong.support.freemarker;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.yelong.support.ognl.OgnlWrapper;

import ognl.OgnlException;

/**
 * 实体映射。
 * 用于存储实体对象
 * 可以设置属性获取时的默认值，防止freemarker解析错误问题
 * 
 * @author pengfei
 * @date 2019年12月23日下午12:08:28
 */
public class EntityMap<T> extends HashMap<String,Object>{

	private static final long serialVersionUID = 5207534605453355713L;

	private T entity;
	
	private String defaultValue;
	
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
		if( null == value ) {
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
	
	@Override
	public Object clone() {
		return this;
	}
	
	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsKey(Object key) {
		return super.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 如果设置的值与 entity 中属性冲突，已 put 值为准
	 */
	public Object put(String key, Object value) {
		return super.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> keySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<Object> values() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		throw new UnsupportedOperationException();
	}

	
}
