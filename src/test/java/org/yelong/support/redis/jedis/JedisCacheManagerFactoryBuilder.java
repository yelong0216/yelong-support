/**
 * 
 */
package org.yelong.support.redis.jedis;

import java.util.Objects;

import redis.clients.jedis.JedisPool;

/**
 * jedis 缓存管理器工厂建造者
 * 
 * @since 1.3
 */
public class JedisCacheManagerFactoryBuilder {

	private JedisPool jedisPool;

	private JsonToObject jsonToObject;

	private ObjectToJson objectToJson;

	public JedisCacheManagerFactoryBuilder() {
	}

	public JedisCacheManagerFactoryBuilder(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public JedisCacheManagerFactoryBuilder setJsonToObject(JsonToObject jsonToObject) {
		this.jsonToObject = jsonToObject;
		return this;
	}

	public JedisCacheManagerFactoryBuilder setObjectToJson(ObjectToJson objectToJson) {
		this.objectToJson = objectToJson;
		return this;
	}

	public JedisCacheManagerFactory build() {
		Objects.requireNonNull(jedisPool);
		Objects.requireNonNull(jsonToObject);
		Objects.requireNonNull(objectToJson);
		return new JedisCacheManagerFactory(jedisPool, jsonToObject, objectToJson);
	}

}
