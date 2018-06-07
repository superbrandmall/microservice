package com.sbm.module.common.init.listener;

import com.sbm.module.common.init.InitAfterLoad;
import com.sbm.module.common.provider.SpringContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Comparator;
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

	private boolean flag = false;

	private void init() {
		if (!flag) {
			flag = true;
			log.info("InitListener: initialization started");
			long startTime = System.currentTimeMillis();
			Map<String, InitAfterLoad> map = provider.getBeans(InitAfterLoad.class);
			map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(e -> {
						Order order = e.getClass().getAnnotation(Order.class);
						return order == null ? 9999 : order.value();
					}
			))).forEachOrdered(e -> {
				log.info(e.getKey() + ": initialization started");
				long startTimeTmp = System.currentTimeMillis();
				// 执行
				e.getValue().init();
				long elapsedTimeTmp = System.currentTimeMillis() - startTimeTmp;
				log.info(e.getKey() + ": initialization completed in " + elapsedTimeTmp + " ms");
			});
			long elapsedTime = System.currentTimeMillis() - startTime;
			log.info("InitListener: total initialization completed in " + elapsedTime + " ms");
		}
	}

}
