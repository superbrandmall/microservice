package com.sbm.module.common.authorization.api.frequency.biz.impl;

import com.sbm.module.common.authorization.api.frequency.biz.IFrequencyService;
import com.sbm.module.common.authorization.api.frequency.domain.Frequency;
import com.sbm.module.common.authorization.exception.AuthorizationCode;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class FrequencyServiceImpl extends CommonServiceImpl implements IFrequencyService {

	@Autowired
	private IRedisService redisService;

	@Override
	public void checkFrequency(Frequency vo) {
		vo.setKey(getKey(vo));
		Integer count = get(vo.getKey());
		vo.setCount(count++);
		set(vo);
		check(vo);
	}

	private String getKey(Frequency vo) {
		return RedisConstant.getKey(Frequency.class, vo.getIp(), vo.getLogin() == null ? StringUtils.EMPTY : vo.getLogin(), vo.getPath());
	}

	private Integer get(String key) {
		return (Integer) redisService.get(key);
	}

	private void set(Frequency vo) {
		vo.setExpire(vo.getTimeout());
		// 存在期限则沿用
		if (redisService.hasExpire(vo.getKey())) {
			vo.setExpire(redisService.getExpire(vo.getKey()));
		}
		// 存入redis
		redisService.set(vo.getKey(), vo.getCount());
		// 设置超时
		redisService.expire(vo.getKey(), vo.getExpire(), TimeUnit.SECONDS);
	}

	private void check(Frequency vo) {
		if (vo.getCount() > vo.getLimit()) {
			throw new BusinessException(AuthorizationCode.F0001, new Object[]{vo.getCount(), vo.getIp(), vo.getLogin(), vo.getPath(),
					vo.getTimeout(), vo.getLimit(), vo.getExpire()});
		}
	}

}
