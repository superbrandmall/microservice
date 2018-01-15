package com.sbm.module.common.init.listener;

import com.sbm.module.common.init.InitAfterLoad;
import com.sbm.module.common.provider.SpringContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class InitListener {

	@Autowired
	private SpringContextProvider provider;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		init();
	}

	private void init() {
		log.info("InitListener: initialization started");
		long startTime = System.currentTimeMillis();
		Map<String, InitAfterLoad> map = provider.getBeans(InitAfterLoad.class);
		for (String key : map.keySet()) {
			log.info(key + ": initialization started");
			long startTimeTmp = System.currentTimeMillis();
			// 执行
			map.get(key).init();
			long elapsedTimeTmp = System.currentTimeMillis() - startTimeTmp;
			log.info(key + ": initialization completed in " + elapsedTimeTmp + " ms");
		}
		long elapsedTime = System.currentTimeMillis() - startTime;
		log.info("InitListener: total initialization completed in " + elapsedTime + " ms");
	}

}
