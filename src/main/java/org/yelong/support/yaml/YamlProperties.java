/**
 * 
 */
package org.yelong.support.yaml;

import java.util.Collection;
import java.util.Map;

import org.yelong.core.annotation.Nullable;

/**
 * yml配置文件
 * @author PengFei
 */
public interface YamlProperties {

	/**
	 * 获取属性值
	 * @param key yml key 如：server.port
	 * @return 键对应的值
	 */
	@Nullable
	String getProperty(String key);
	
	/**
	 * 获取属性值
	 * @param key key
	 * @param defaultValue 默认值
	 * @return 如果key值不存在则返回defaultValue，否则返回对应值
	 */
	String getProperty(String key,String defaultValue);
	
	/**
	 * 将配置装配为对象
	 * @param <T> 
	 * @param type 类型
	 * @param prefix 前缀
	 * @return 装配的对象
	 */
	<T> T as(String prefix,Class<T> type);
	
	/**
	 * 获取源映射
	 * @return yaml直接转换的map
	 */
	Map<String,Object> getSourceMap();
	
	/**
	 * 将源映射全部转换为键值对的映射
	 * @return
	 */
	Map<String,String> getMap();
	
	/**
	 * 获取所有的键值
	 * @return
	 */
	Collection<String> getKeys();
	
	/**
	 * 获取yaml文件名称
	 * @return
	 */
	@Nullable
	String getName();
	
}
