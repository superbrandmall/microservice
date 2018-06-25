package com.sbm.module.onlineleasing.customer.base.info.floor.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import com.sbm.module.onlineleasing.customer.base.info.floor.biz.IFloorInfoService;
import com.sbm.module.onlineleasing.domain.base.info.floor.FloorInfo;
import com.sbm.module.onlineleasing.domain.base.info.floor.FloorMinInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorInfoServiceImpl extends CommonServiceImpl implements IFloorInfoService {

	@Autowired
	private IRedisService redisService;

	@Override
	public FloorInfo findOneByMallCodeAndDescription(String mallCode, String description) {
		FloorInfo floorInfo = null;
		String valuer = (String) redisService.get(RedisConstant.getKey(FloorInfo.class, mallCode, description));
		if (StringUtils.isNotBlank(valuer)) {
			floorInfo = JSON.parseObject(valuer, FloorInfo.class);
		}
		return floorInfo;
	}

	@Override
	public List<FloorMinInfo> findAll() {
		List<FloorMinInfo> vos = null;
		String valuer = (String) redisService.get(RedisConstant.getKey(FloorMinInfo.class, RedisConstant.LIST));
		if (StringUtils.isNotBlank(valuer)) {
			vos = JSON.parseArray(valuer, FloorMinInfo.class);
		}
		return vos;
	}
}
