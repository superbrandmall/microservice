package com.sbm.module.sync.hd.api.modality.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.sync.hd.api.modality.biz.IModalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ModalityServiceImpl extends CommonServiceImpl implements IModalityService {

	@Autowired
	private IRedisService redisService;



	private static final String PREFIX = "MODALITY_";

	@Override
	@Scheduled(cron = "0 0 0 * * ?")
	public void refresh() {
		System.out.println("1231231231231123123");

	}

}
