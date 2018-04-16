package org.ifsim.vairline.util;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
	private volatile JedisConnectionFactory jedisConnectionFactory;
	private volatile RedisTemplate<String, String> redisTemplate;
	private volatile RedisCacheManager redisCacheManager;

	public RedisCacheConfig() {
		super();
	}

	/**
	 * 带参数的构造方法 初始化所有的成员变量
	 * 
	 * @param jedisConnectionFactory
	 * @param redisTemplate
	 * @param redisCacheManager
	 */
	public RedisCacheConfig(JedisConnectionFactory jedisConnectionFactory, RedisTemplate<String, String> redisTemplate,
			RedisCacheManager redisCacheManager) {
		this.jedisConnectionFactory = jedisConnectionFactory;
		this.redisTemplate = redisTemplate;
		this.redisCacheManager = redisCacheManager;
	}

	public JedisConnectionFactory getJedisConnecionFactory() {
		return jedisConnectionFactory;
	}

	public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}

	public RedisCacheManager getRedisCacheManager() {
		return redisCacheManager;
	}

	/**
	 * @Description: 自定义键
	 */
	@Override
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... objects) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : objects) {
					if (obj != null) {
						if(obj.getClass().getSuperclass().getName().equals("org.ifsim.vairline.common.orm.BaseEntity")) {
							Method m1 = null;
							try {
								m1 = obj.getClass().getMethod("getId");
								sb.append(m1.invoke(obj));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						sb.append(obj.getClass());
						sb.append(obj.toString());
					}
				}
				return sb.toString();
			}
		};
	}
}
