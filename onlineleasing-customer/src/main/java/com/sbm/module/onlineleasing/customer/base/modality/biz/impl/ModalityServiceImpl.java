package com.sbm.module.onlineleasing.customer.base.modality.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import com.sbm.module.onlineleasing.customer.base.modality.biz.IModalityService;
import com.sbm.module.onlineleasing.domain.base.modality.Modality;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModalityServiceImpl implements IModalityService {

	@Autowired
	private IRedisService redisService;

	@Override
	public List<Modality> findAll() {
		List<Modality> children = null;
		String valuer = (String) redisService.get(RedisConstant.getKey(Modality.class, RedisConstant.LIST));
		if (StringUtils.isNotBlank(valuer)) {
			children = JSON.parseArray(valuer, Modality.class);
		}
		return children;
	}
}
