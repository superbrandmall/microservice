package com.sbm.module.onlineleasing.customer.base.info.mall.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import com.sbm.module.onlineleasing.customer.base.info.mall.biz.IMallInfoService;
import com.sbm.module.onlineleasing.domain.base.info.mall.MallInfo;
import com.sbm.module.onlineleasing.domain.base.info.mall.MallMinInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallInfoServiceImpl extends CommonServiceImpl implements IMallInfoService {

	@Autowired
	private IRedisService redisService;

	@Override
	public MallInfo findOneByMallCode(String mallCode) {
		MallInfo mallInfo = null;
		String valuer = (String) redisService.get(RedisConstant.getKey(MallInfo.class, mallCode));
		if (StringUtils.isNotBlank(valuer)) {
			mallInfo = JSON.parseObject(valuer, MallInfo.class);
		}
		return mallInfo;
	}

	@Override
	public List<MallMinInfo> findAllOrderByPosition() {
		List<MallMinInfo> vos = null;
		String valuer = (String) redisService.get(RedisConstant.getKey(MallInfo.class, RedisConstant.LIST));
		if (StringUtils.isNotBlank(valuer)) {
			vos = JSON.parseArray(valuer, MallMinInfo.class);
		}
		return vos;
	}

}
