/**
 * 
 */
package org.yelong.support.redis.jedis;

import org.yelong.core.annotation.Nullable;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 默认的Jedis缓存管理器实现
 * 
 * @author PengFei
 * @since 1.3.0
 */
public class DefaultJedisCacheManagerImpl extends BaseJedisCacheManager {

	private final JedisPool jedisPool;

	protected DefaultJedisCacheManagerImpl(@Nullable String keyPrefix, @Nullable String name, final JedisPool jedisPool,
			ObjectToJson objectToJson, JsonToObject jsonToObject) {
		super(keyPrefix, name, objectToJson, jsonToObject);
		this.jedisPool = jedisPool;
	}

	@Override
	protected Jedis getJedis() {
		return jedisPool.getResource();
	}

}
