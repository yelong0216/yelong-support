package org.yelong.support.redis.jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.yelong.core.annotation.Nullable;
import org.yelong.core.cache.CacheEntity;
import org.yelong.core.cache.CacheEntitys;
import org.yelong.core.cache.DefaultCacheEntity;

import redis.clients.jedis.Jedis;

/**
 * 抽象的jedis缓存管理器
 * 
 * 默认存储为Json
 * 
 * @since 1.3
 */
public abstract class AbstractJedisCacheManager implements JedisCacheManager {

	@Override
	public <T> CacheEntity<T> putCache(String key, CacheEntity<T> cacheEntity) {
		T entity = cacheEntity.get();
		if (null == entity) {
			return null;
		}
		String redisKey = getRedisKey(key);
		getJedis().set(redisKey, objectToJson(entity));
		getJedis().set(getValueClassNameKey(key), entity.getClass().getName());
		return cacheEntity;
	}

	@Override
	public <T> CacheEntity<T> putCache(String key, T entity) {
		return putCache(key, new DefaultCacheEntity<T>(entity));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> CacheEntity<T> getCache(String key) {
		String redisKey = getRedisKey(key);
		String value = getJedis().get(redisKey);
		if (StringUtils.isBlank(value)) {
			return CacheEntitys.emptyCacheEntity();
		}
		String valueClassNameKey = getValueClassNameKey(key);
		String valueClassName = getJedis().get(valueClassNameKey);
		if (StringUtils.isBlank(valueClassName)) {
			throw new RuntimeException("没有找到" + key + "值的类型！");
		}
		Class<?> valueClass;
		try {
			valueClass = ClassUtils.getClass(valueClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		Object entity = jsonToObject(value, valueClass);
		return new DefaultCacheEntity<T>((T) entity);
	}

	@Override
	public Map<String, CacheEntity<?>> getCacheAll() {
		Set<String> keys = getKeys();
		Map<String, CacheEntity<?>> caches = new HashMap<String, CacheEntity<?>>();
		keys.forEach(x -> caches.put(x, getCache(x)));
		return caches;
	}

	@Override
	public boolean containsKey(String key) {
		String redisKey = getRedisKey(key);
		return StringUtils.isNotBlank(getJedis().get(redisKey));
	}

	@Override
	public <T> CacheEntity<T> remove(String key) {
		String redisKey = getRedisKey(key);
		String valueClassNameKey = getValueClassNameKey(key);
		CacheEntity<T> cache = getCache(key);
		getJedis().del(redisKey, valueClassNameKey);
		return cache;
	}

	@Override
	public void removeQuietly(String key) {
		String redisKey = getRedisKey(key);
		String valueClassNameKey = getValueClassNameKey(key);
		getJedis().del(redisKey, valueClassNameKey);
	}

	@Override
	public Set<String> getKeys() {
		String keyPrefix = getKeyPrefix();
		if (StringUtils.isBlank(keyPrefix)) {
			return getJedis().keys("*");
		}
		Set<String> keys = getJedis().keys(keyPrefix + "*");
		return keys.stream().map(x -> x.substring(keyPrefix.length() + 1)).collect(Collectors.toSet());
	}

	@Override
	public void clear() {
		Set<String> keys = getKeys();
		keys.forEach(this::remove);
	}

	/**
	 * 对象转换为json
	 * 
	 * @param obj obj
	 * @return json
	 */
	protected abstract String objectToJson(Object obj);

	/**
	 * json转换为对象
	 * 
	 * @param <T>       class type
	 * @param json      json
	 * @param classType class type
	 * @return 转换后的对象
	 */
	protected abstract <T> T jsonToObject(String json, Class<T> classType);

	/**
	 * @return jedis
	 */
	protected abstract Jedis getJedis();

	/**
	 * 键值前缀
	 * 
	 * 存储在redis中的键值通过添加的缓存键值与键值前缀进行拼接后作为redis的key值<br/>
	 * 
	 * 这不会映射该管理器的其他功能方法<br/>
	 * 
	 * 对于多个Redis缓存管理器，指定不同的键值前缀可以使每个管理器管理不同区域的缓存，防止对Redis中其他的缓存值进行误操作
	 * 
	 * @return 键值前缀
	 */
	@Nullable
	protected abstract String getKeyPrefix();

	/**
	 * @param key 管理器使用者传入的键值
	 * @return 包装为Redis中使用的键值
	 */
	protected abstract String getRedisKey(String key);

	/**
	 * 由于在Redis中存储的json格式的信息，所以需要将存储的对象类型也存储到Redis中
	 * 
	 * @param key 管理器使用者传入的键值
	 * @return 值类型的键值
	 */
	protected abstract String getValueClassNameKey(String key);

}
