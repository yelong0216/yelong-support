/**
 * 
 */
package org.yelong.support.redis.jedis.test;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.yelong.core.cache.CacheEntity;
import org.yelong.core.cache.CacheManager;
import org.yelong.support.redis.jedis.JedisCacheManagerFactory;
import org.yelong.support.redis.jedis.JedisCacheManagerFactoryBuilder;
import org.yelong.support.redis.jedis.JedisCacheManagers;
import org.yelong.support.test.json.gson.User;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author PengFei
 *
 */
public class RedisCacheTest {

	private static RedisCacheTest redisCacheTest = new RedisCacheTest();
	
	public static final Jedis jedis = new Jedis("localhost");
	
	public static final Gson gson = new Gson();
	
	public static final CacheManager cacheManager;
	
	static {
		JedisPool jedisPool = new JedisPool("localhost");
		JedisCacheManagerFactory factory = new JedisCacheManagerFactoryBuilder(jedisPool)
				.setJsonToObject(gson::fromJson)
				.setObjectToJson(gson::toJson)
				.build();
		JedisCacheManagers.setKeyPrefix("labbol");
		cacheManager = factory.create("dict");
		
	}
	
	
	@Test
	public void set() {
		User user = new User();
		user.setAge(10);
		user.setPassword("123456");
		cacheManager.putCache("user", user);
		
		CacheEntity<User> cacheEntity = cacheManager.getCache("user");
		System.out.println(cacheEntity.get().getAge());
	}
	
	@Test
	public void containsKey() {
		boolean containsKey = cacheManager.containsKey("user");
		System.out.println(containsKey);
	}
	
	@Test
	public void delete() {
		cacheManager.remove("user");
	}
	
	@Test
	public void deleteAll() {
		Set<String> keys = jedis.keys("*");
		keys.forEach(jedis::del);
	}
	
	@Test
	public void get() {
		CacheEntity<User> user = cacheManager.getCache("user");
		User user2 = user.get();
		System.out.println(user2);
	}
	
	@Test
	public void keys() {
		Set<String> keys = cacheManager.getKeys();
		keys.forEach(System.out::println);
	}
	
	@Test
	public void maps() {
		Map<String, CacheEntity<?>> cacheAll = cacheManager.getCacheAll();
		for (Entry<String, CacheEntity<?>> entry : cacheAll.entrySet()) {
			System.out.println(entry.getKey()+"---"+entry.getValue());
		}
	}
	
	@Test
	public void jedjskeys() {
		Set<String> keys = jedis.keys("*");
		for (String key : keys) {
//			System.out.println(key+"---"+jedis.hget(key));
			System.out.println(key);
		}
	}
	
	
	public static void main(String[] args) {
		redisCacheTest.keys();
	}
	
}
