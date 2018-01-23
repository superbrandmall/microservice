package com.sbm.module.sync.bi;

import com.sbm.module.common.redis.biz.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class BiJob {

	@Autowired
	private IRedisService redisService;

	/**
	 * 每隔5秒执行, 单位：ms。
	 */
	@Scheduled(fixedRate = 5000)
	public void testFixRate() {


	}



}
