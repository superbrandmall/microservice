package com.sbm.module.sync.hd.api.modality.biz.impl;

import com.alibaba.fastjson.JSON;
import com.netflix.discovery.converters.Auto;
import com.sbm.module.common.biz.impl.BusinessServiceImpl;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.onlineleasing.base.modality.biz.ITOLModalityService;
import com.sbm.module.onlineleasing.base.modality.domain.TOLModality;
import com.sbm.module.partner.hd.api.biztype.client.IHdBiztypeClient;
import com.sbm.module.partner.hd.api.biztype.domain.HdBiztype;
import com.sbm.module.sync.hd.api.modality.biz.IModalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModalityServiceImpl extends BusinessServiceImpl<TOLModality, HdBiztype> implements IModalityService {

	@Autowired
	private IRedisService redisService;
	@Autowired
	private IHdBiztypeClient hdBiztypeClient;
	@Autowired
	private ITOLModalityService modalityService;

	private static final String PREFIX = "MODALITY_";

	@Override
	@Scheduled(cron = "0 0 0 * * ?")
	public void refresh() {
		JsonContainer<List<HdBiztype>> result = hdBiztypeClient.findAllVo();
		System.out.println(JSON.toJSONString(result));
		findAll(result.getData());
	}

	@Override
	public TOLModality newInstance(HdBiztype e) {
		TOLModality po = modalityService.findOneByCode(e.getCode());
		System.out.println(JSON.toJSONString(po));
		return null;
	}
}
