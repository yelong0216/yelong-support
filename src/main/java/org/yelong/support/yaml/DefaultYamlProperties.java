/**
 * 
 */
package org.yelong.support.yaml;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

import org.yelong.core.annotation.Nullable;
import org.yelong.support.ognl.OgnlWrapper;

import ognl.OgnlException;

/**
 * @author pengfei<yl1430834495@163.com>
 * @date 2019年11月14日上午10:25:30
 * @version 1.2
 */
public class DefaultYamlProperties implements YamlProperties{

	@Nullable
	private String name;
	
	private final Map<String,Object> sourceMap;
	
	private Properties properties;
	
	private YamlMapToProperties yamlMapToProperties = new YamlMapToProperties();
	
	public DefaultYamlProperties(Map<String,Object> sourceMap) {
		this(null,sourceMap);
	}
	
	public DefaultYamlProperties(@Nullable String name , Map<String,Object> sourceMap) {
		this.name = name;
		this.sourceMap = sourceMap;
		this.properties = yamlMapToProperties.as(sourceMap);
	}
	
	@Override
	public String getProperty(String key) {
		return this.properties.getProperty(key);
	}

	@Override
	public String getProperty(String key, String defaultValue) {
		return this.properties.getProperty(key, defaultValue);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T as(String prefix,Class<T> type) {
		OgnlWrapper ognlWrapper = new OgnlWrapper();
		try {
			ognlWrapper.setRoot(type.newInstance());
			Collection<String> keys = getKeys();
			for (String key : keys) {
				if( key.startsWith(prefix+".") ) {
					try {
						ognlWrapper.setValue(key.substring((prefix+".").length()), this.properties.get(key));
					} catch (OgnlException e) {
						
					}
				}
			}
			return (T) ognlWrapper.getRoot();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> getSourceMap() {
		return this.sourceMap;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, String> getMap() {
		return (Map)this.properties;
	}

	@SuppressWarnings({ "unchecked", "rawtypes"})
	@Override
	public Collection<String> getKeys() {
		return (Collection)this.properties.keySet();
	}

	@Override
	public String getName() {
		return this.name;
	}
	

}
