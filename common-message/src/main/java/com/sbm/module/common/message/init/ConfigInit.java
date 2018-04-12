package com.sbm.module.common.message.init;

import com.sbm.module.common.init.InitAfterLoad;
import com.sbm.module.common.message.api.config.biz.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigInit implements InitAfterLoad {

	@Autowired
	private IConfigService configService;

	@Override
	public void init() {
		configService.refresh();
	}
}
