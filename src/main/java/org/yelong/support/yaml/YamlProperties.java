/**
 * 
 */
package org.yelong.support.yaml;

import java.util.Collection;
import java.util.Map;

import org.yelong.core.annotation.Nullable;

/**
 * @author pengfei<yl1430834495@163.com>
 * @date 2019年11月12日上午11:34:59
 * @version 1.2
 */
public interface YamlProperties {

	/**
	 * 获取属性值
	 * @date 2019年11月14日上午10:05:26
	 * @version 1.2
	 * @param key yml key 如：server.port
	 * @return 键对应的值
	 */
	@Nullable
	String getProperty(String key);
	
	/**
	 * 获取属性值
	 * @date 2019年11月14日上午10:07:12
	 * @version 1.2
	 * @param key 
	 * @param defaultValue 默认值
	 * @return 如果key值不存在则返回defaultValue，否则返回对应值
	 */
	String getProperty(String key,String defaultValue);
	
	/**
	 * 将配置装配为对象
	 * @date 2019年11月14日上午10:24:44
	 * @version 1.2
	 * @param <T> 
	 * @param type 类型
	 * @param prefix 前缀
	 * @return 装配的对象
	 */
	<T> T as(String prefix,Class<T> type);
	
	/**
	 * 获取源映射
	 * @date 2019年11月14日上午10:10:04
	 * @version 1.2
	 * @return yaml直接转换的map
	 */
	Map<String,Object> getSourceMap();
	
	/**
	 * 将源映射全部转换为键值对的映射
	 * @date 2019年11月14日上午10:24:04
	 * @version 1.2
	 * @return
	 */
	Map<String,String> getMap();
	
	/**
	 * 获取所有的键值
	 * @date 2019年11月14日上午10:23:10
	 * @version 1.2
	 * @return
	 */
	Collection<String> getKeys();
	
	/**
	 * 获取yaml文件名称
	 * @date 2019年11月14日上午10:05:05
	 * @version 1.2
	 * @return
	 */
	@Nullable
	String getName();
	
	
}
