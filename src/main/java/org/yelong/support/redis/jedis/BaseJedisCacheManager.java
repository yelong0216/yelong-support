/**
 * 
 */
package org.yelong.support.redis.jedis;

import org.apache.commons.lang3.StringUtils;
import org.yelong.core.annotation.Nullable;

/**
 * 基础的Jedis缓存管理器
 * 
 * @author PengFei
 * @since 1.3.0
 */
public abstract class BaseJedisCacheManager extends AbstractJedisCacheManager {

	private final String keyPrefix;

	private final String name;

	private final ObjectToJson objectToJson;

	private final JsonToObject jsonToObject;

	protected BaseJedisCacheManager(@Nullable String keyPrefix, @Nullable String name, ObjectToJson objectToJson,
			JsonToObject jsonToObject) {
		this.name = name;
		this.keyPrefix = keyPrefix;
		this.objectToJson = objectToJson;
		this.jsonToObject = jsonToObject;
	}

	public String getName() {
		return name;
	}

	protected String getKeyPrefix() {
		if (StringUtils.isBlank(keyPrefix)) {
			if (StringUtils.isBlank(name)) {
				return null;
			} else {
				return name;
			}
		} else {
			if (StringUtils.isBlank(name)) {
				return keyPrefix;
			} else {
				return keyPrefix + ":" + name;
			}
		}
	}

	protected String getRedisKey(String key) {
		return getKeyPrefix() + ":" + key;
	}

	protected String getValueClassNameKey(String key) {
		return "value:class:" + getRedisKey(key);
	}

	@Override
	protected String objectToJson(Object obj) {
		return objectToJson.toJson(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <T> T jsonToObject(String json, Class<T> classType) {
		return (T) jsonToObject.toObject(json, classType);
	}

}
