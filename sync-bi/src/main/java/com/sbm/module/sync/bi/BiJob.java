package com.sbm.module.sync.bi;

import com.sbm.module.sync.bi.api.bi.biz.IBiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class BiJob {

	@Autowired
	private IBiService service;

	@Scheduled(cron = "0 0 0 * * ?")
	public void execute() {
		log.info("暂时先这么写日志: " + new Date());
		service.refresh();
	}
}
