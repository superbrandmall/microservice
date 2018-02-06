package com.sbm.module.sync.hd.api.modality.biz.impl;

import com.alibaba.fastjson.JSON;
import com.netflix.discovery.converters.Auto;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.partner.hd.api.biztype.client.IHdBiztypeClient;
import com.sbm.module.partner.hd.api.biztype.domain.HdBiztype;
import com.sbm.module.sync.hd.api.modality.biz.IModalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModalityServiceImpl extends CommonServiceImpl implements IModalityService {

	@Autowired
	private IRedisService redisService;
	@Autowired
	private IHdBiztypeClient hdBiztypeClient;

	private static final String PREFIX = "MODALITY_";

	@Override
	@Scheduled(cron = "0 0 0 * * ?")
	public void refresh() {
		JsonContainer<List<HdBiztype>> result = hdBiztypeClient.findAllVo();
		System.out.println(JSON.toJSONString(result));
	}

}
