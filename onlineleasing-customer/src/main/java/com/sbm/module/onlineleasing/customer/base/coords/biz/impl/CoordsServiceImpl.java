package com.sbm.module.onlineleasing.customer.base.coords.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import com.sbm.module.onlineleasing.customer.base.coords.biz.ICoordsService;
import com.sbm.module.onlineleasing.domain.base.coords.Coords;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordsServiceImpl implements ICoordsService {

	@Autowired
	private IRedisService redisService;

	@Override
	public List<Coords> findAllByMallCodeAndDescription(String mallCode, String description) {
		List<Coords> vos = null;
		String valuer = (String) redisService.get(RedisConstant.getKey(Coords.class, mallCode, description));
		if (StringUtils.isNotBlank(valuer)) {
			vos = JSON.parseArray(valuer, Coords.class);
		}
		return vos;
	}
}
