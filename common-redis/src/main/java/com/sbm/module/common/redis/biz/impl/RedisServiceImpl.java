package com.sbm.module.common.redis.biz.impl;

import com.sbm.module.common.redis.biz.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

@Component
public class RedisServiceImpl implements IRedisService {

	@Autowired
	private RedisTemplate redisTemplate;
	/**
	 * 默认超时10 * 60 秒
	 */
	private Long timeout = 10 * 60L;

	/**
	 * 无期限
	 */
	private static Long noExpire = -1L;
	/**
	 * 已过期
	 */
	private static Long isExpired = -2L;

	public void set(String key, Object obj) {
		ValueOperations<String, Object> value = redisTemplate.opsForValue();
		value.set(key, obj);
	}

	public Object get(String key) {
		ValueOperations<String, Object> value = redisTemplate.opsForValue();
		return value.get(key);
	}

	public Object getAndSet(String key, Object obj) {
		ValueOperations<String, Object> value = redisTemplate.opsForValue();
		return value.getAndSet(key, obj);
	}

	public Boolean expire(String key, long timeout, TimeUnit unit) {
		return redisTemplate.expire(key, timeout, unit);
	}

	public Boolean defaultExpire(String key) {
		return expire(key, timeout, TimeUnit.SECONDS);
	}

	public Long getExpire(String key) {
		return redisTemplate.getExpire(key);
	}

	public Long getExpire(String key, TimeUnit unit) {
		return redisTemplate.getExpire(key, unit);
	}

	public boolean hasExpire(String key) {
		Long expire = getExpire(key);
		if (isExpired == expire || noExpire == expire) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public void delete(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}

	public void set2redis(String key, Object value) {
		set2redis(key, value, null, TimeUnit.SECONDS);
	}

	public void set2redis(String key, Object value, Long timeout, TimeUnit unit) {
		set(key, value);
		// 设置有效期
		if (null == timeout) {
			defaultExpire(key);
		} else {
			expire(key, timeout, unit);
		}
	}

}
