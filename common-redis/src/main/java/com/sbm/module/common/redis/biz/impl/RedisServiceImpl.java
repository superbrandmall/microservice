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
	 * 默认超时1天
	 */
	private Long OneDay = 1L;

	/**
	 * 默认超时2天
	 */
	private Long TwoDays = 2L;

	/**
	 * 默认超时12小时
	 */
	private Long TwelveHours = 12L;

	/**
	 * 无期限
	 */
	private static Long noExpire = -1L;
	/**
	 * 已过期
	 */
	private static Long isExpired = -2L;

	@Override
	public void set(String key, Object obj) {
		ValueOperations<String, Object> value = redisTemplate.opsForValue();
		value.set(key, obj);
	}

	@Override
	public Object get(String key) {
		ValueOperations<String, Object> value = redisTemplate.opsForValue();
		return value.get(key);
	}

	@Override
	public Object getAndSet(String key, Object obj) {
		ValueOperations<String, Object> value = redisTemplate.opsForValue();
		return value.getAndSet(key, obj);
	}

	@Override
	public Boolean expire(String key, long timeout, TimeUnit unit) {
		return redisTemplate.expire(key, timeout, unit);
	}

	@Override
	public Boolean defaultExpire(String key) {
		return expire(key, timeout, TimeUnit.SECONDS);
	}

	@Override
	public Long getExpire(String key) {
		return redisTemplate.getExpire(key);
	}

	@Override
	public Long getExpire(String key, TimeUnit unit) {
		return redisTemplate.getExpire(key, unit);
	}

	@Override
	public boolean hasExpire(String key) {
		Long expire = getExpire(key);
		if (isExpired == expire || noExpire == expire) {
			return false;
		}
		return true;
	}

	@Override
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

	@Override
	public void set2Redis(String key, Object value) {
		set2Redis(key, value, null, TimeUnit.SECONDS);
	}

	@Override
	public void set2Redis(String key, Object value, Long timeout, TimeUnit unit) {
		set(key, value);
		// 设置有效期
		if (null == timeout) {
			defaultExpire(key);
		} else {
			expire(key, timeout, unit);
		}
	}

	@Override
	public void set2RedisOneDay(String key, Object value) {
		set2Redis(key, value, TwoDays, TimeUnit.DAYS);
	}

	@Override
	public void set2RedisTwoDays(String key, Object value) {
		set2Redis(key, value, TwoDays, TimeUnit.DAYS);
	}

	@Override
	public void set2RedisTwelveHours(String key, Object value) {
		set2Redis(key, value, TwelveHours, TimeUnit.HOURS);
	}
}
