/**
 * 
 */
package org.yelong.support.redis.jedis;

/**
 * 对象转换为json的转换器
 * 
 * @author PengFei
 * @since 1.3.0
 */
@FunctionalInterface
public interface ObjectToJson {

	/**
	 * 将对象转换为json
	 * 
	 * @param obj 对象
	 * @return json
	 */
	String toJson(Object obj);

}
