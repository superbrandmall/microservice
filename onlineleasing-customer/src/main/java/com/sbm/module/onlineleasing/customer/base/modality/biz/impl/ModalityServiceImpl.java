package com.sbm.module.onlineleasing.customer.base.modality.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import com.sbm.module.onlineleasing.base.modality.biz.ITOLModalityService;
import com.sbm.module.onlineleasing.customer.base.modality.biz.IModalityService;
import com.sbm.module.onlineleasing.domain.base.modality.Modality;
import com.sbm.module.onlineleasing.domain.base.modality.ModalityMaxInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModalityServiceImpl extends CommonServiceImpl implements IModalityService {

	@Autowired
	private IRedisService redisService;
	@Autowired
	private ITOLModalityService modalityService;

	@Override
	public List<Modality> findAll() {
		List<Modality> children = null;
		String valuer = (String) redisService.get(RedisConstant.getKey(Modality.class, RedisConstant.LIST));
		if (StringUtils.isNotBlank(valuer)) {
			children = JSON.parseArray(valuer, Modality.class);
		}
		return children;
	}

	@Override
	public ModalityMaxInfo findOneByCode(String code) {
		return mapOneIfNotNull(modalityService.findOneByCode(code), e -> new ModalityMaxInfo(e.getCode(), e.getName(), e.getLv(), e.getRemark(),
				e.getHdUuid(), e.getHdLevelid()));
	}
}
