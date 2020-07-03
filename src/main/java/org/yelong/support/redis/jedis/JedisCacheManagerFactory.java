package org.yelong.support.redis.jedis;

import java.util.ArrayList;
import java.util.List;

import org.yelong.core.cache.CacheManager;
import org.yelong.core.cache.CacheManagerFactory;

import redis.clients.jedis.JedisPool;

/**
 * jedis缓存管理器工厂
 * 
 * @author PengFei
 * @since 1.3.0
 */
public class JedisCacheManagerFactory implements CacheManagerFactory {

	private final JedisPool jedisPool;

	private final JsonToObject jsonToObject;

	private final ObjectToJson objectToJson;

	private final List<CacheManager> cacheManagers = new ArrayList<CacheManager>();

	public JedisCacheManagerFactory(JedisPool jedisPool, JsonToObject jsonToObject, ObjectToJson objectToJson) {
		this.jedisPool = jedisPool;
		this.jsonToObject = jsonToObject;
		this.objectToJson = objectToJson;
	}

	@Override
	public CacheManager create() {
		return create(null);
	}

	@Override
	public CacheManager create(String name) {
		JedisCacheManagers.notAllowSetKeyPrefix();
		DefaultJedisCacheManagerImpl cacheManager = new DefaultJedisCacheManagerImpl(JedisCacheManagers.getKeyPrefix(),
				name, jedisPool, objectToJson, jsonToObject);
		cacheManagers.add(cacheManager);
		return cacheManager;
	}

	@Override
	public List<CacheManager> getHasCreate() {
		return cacheManagers;
	}

}
