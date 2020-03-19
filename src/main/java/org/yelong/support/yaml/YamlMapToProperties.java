/**
 * 
 */
package org.yelong.support.yaml;

import java.util.Map;
import java.util.Properties;

import org.yelong.core.annotation.Nullable;

/**
 * 
 * yaml配置map装换为properties
 * 
 * @author pengfei<yl1430834495@163.com>
 * @date 2019年11月14日上午10:45:17
 * @version 1.2
 */
public class YamlMapToProperties {

	public YamlMapToProperties() {
		
	}
	
	public Properties as(Map<String,Object> map) {
		return as(map, new Properties());
	}
	
	public Properties as(Map<String,Object> map,Properties properties) {
		to(null, map, properties);
		return properties;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void to(@Nullable String prefix,Map<String, Object> map,Properties properties ) {
		map.forEach((k,v)->{
			String cliedPrefix = prefix == null ? k : prefix + "." + k;
			if( v instanceof Map ) {
				to(cliedPrefix, (Map)v, properties);
			} else {
				properties.setProperty(cliedPrefix, v.toString());
			}
		});
	}
	
	
}
