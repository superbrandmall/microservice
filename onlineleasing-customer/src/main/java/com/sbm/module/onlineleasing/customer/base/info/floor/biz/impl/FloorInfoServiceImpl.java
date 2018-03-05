package com.sbm.module.onlineleasing.customer.base.info.floor.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.onlineleasing.constant.RedisConstant;
import com.sbm.module.onlineleasing.customer.base.info.floor.biz.IFloorInfoService;
import com.sbm.module.onlineleasing.domain.info.floor.FloorInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FloorInfoServiceImpl implements IFloorInfoService {

	@Autowired
	private IRedisService redisService;

	@Override
	public FloorInfo findOneByMallCodeAndDescription(String mallCode, String description) {
		FloorInfo floorInfo = null;
		String valuer = (String) redisService.get(RedisConstant.getKey(RedisConstant.FLOOR_INFO, mallCode, description));
		if (StringUtils.isNotBlank(valuer)) {
			floorInfo = JSON.parseObject(valuer, FloorInfo.class);
		}
		return floorInfo;
	}
}
