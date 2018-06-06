package com.sbm.module.common.redis.biz;

import java.util.concurrent.TimeUnit;

public interface IRedisService {

	/**
	 * 设置缓存
	 *
	 * @param key
	 * @param obj
	 */
	void set(String key, Object obj);

	/**
	 * 获取缓存
	 *
	 * @param key
	 * @return
	 */
	Object get(String key);

	/**
	 * 覆盖
	 *
	 * @param key
	 * @param obj
	 * @return
	 */
	Object getAndSet(String key, Object obj);

	/**
	 * 设置缓存超时
	 *
	 * @param key
	 * @param timeout
	 * @param unit
	 * @return
	 */
	Boolean expire(String key, long timeout, TimeUnit unit);

	/**
	 * 设置缓存超默认配置 10*60秒
	 *
	 * @param key
	 * @return
	 */
	Boolean defaultExpire(String key);

	/**
	 * 返回超时剩余时间
	 *
	 * @param key
	 * @return
	 */
	Long getExpire(String key);

	/**
	 * 返回超时剩余时间，带时间单元
	 *
	 * @param key
	 * @param unit
	 * @return
	 */
	Long getExpire(String key, TimeUnit unit);

	/**
	 * 有期限
	 *
	 * @param key
	 * @return
	 */
	boolean hasExpire(String key);

	/**
	 * 删除缓存,根据key精确匹配删除
	 *
	 * @param key
	 */
	void delete(String... key);

	/**
	 * 存入redis
	 *
	 * @param key
	 * @param value
	 */
	void set2Redis(String key, Object value);

	/**
	 * 存入redis自定过期时长
	 *
	 * @param key
	 * @param value
	 * @param timeout
	 * @param unit
	 */
	void set2Redis(String key, Object value, Long timeout, TimeUnit unit);

	/**
	 * 存入redis1天
	 *
	 * @param key
	 * @param value
	 */
	void set2RedisOneDay(String key, Object value);

	/**
	 * 存入redis2天
	 *
	 * @param key
	 * @param value
	 */
	void set2RedisTwoDays(String key, Object value);

	/**
	 * 存入redis12小时
	 *
	 * @param key
	 * @param value
	 */
	void set2RedisTwelveHours(String key, Object value);
}
