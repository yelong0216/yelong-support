/**
 * 
 */
package org.yelong.support.redis.jedis;

/**
 * json 转换为对象的转换器
 * 
 * @author PengFei
 * @since 1.3.0
 */
@FunctionalInterface
public interface JsonToObject {

	/**
	 * 将json转换为指定的类型对象
	 * 
	 * @param json      json
	 * @param classType 类型
	 * @return 对象
	 */
	Object toObject(String json, Class<?> classType);

}
