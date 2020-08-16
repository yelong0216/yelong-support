/**
 * 
 */
package org.yelong.support.redis.jedis;

/**
 * jedis 缓存管理器工具类
 * 
 * @since 1.3
 */
public final class JedisCacheManagers {

	private JedisCacheManagers() {
	}

	private static final String DEFAULT_KEY_PREFIX = "yelong:support";

	private static String KEY_PREFIX = DEFAULT_KEY_PREFIX;

	private static boolean keyPrefixFlag = false;

	public static String getKeyPrefix() {
		return KEY_PREFIX;
	}

	public static synchronized void setKeyPrefix(String keyPrefix) {
		if (keyPrefixFlag == true) {
			throw new RuntimeException("redis缓存的键值前缀只允许被修改一次。保证整个项目中所有缓存对象使用相同的前缀");
		}
		KEY_PREFIX = keyPrefix;
		keyPrefixFlag = true;
	}

	/**
	 * 是否已经修改过键值前缀
	 */
	public static synchronized boolean isSetKeyPrefix() {
		return keyPrefixFlag == true;
	}

	/**
	 * 默认情况下只有在设置一次键值前缀时才会禁止在此修改键值前缀<br/>
	 * 可以通过此方法直接禁用修改键值前缀
	 */
	public static synchronized void notAllowSetKeyPrefix() {
		keyPrefixFlag = true;
	}

}
