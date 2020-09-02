/**
 * 
 */
package org.yelong.support.redis.jedis.test;

import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * @author PengFei
 *
 */
public class RedisTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		System.out.println(jedis.ping());
		jedis.set("key1", "123456");
		System.out.println(jedis.get("key1"));
		System.out.println(jedis.get("123456"));
		Set<String> keys = jedis.keys("spring*");
		keys.forEach(System.out::println);
	}
	
}
