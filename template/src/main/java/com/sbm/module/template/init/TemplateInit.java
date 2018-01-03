package com.sbm.module.template.init;

import com.sbm.module.common.init.InitAfterLoad;
import com.sbm.module.template.init.biz.ITemplateInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TemplateInit implements InitAfterLoad {

	@Autowired
	private ITemplateInitService service;

	@Override
	public void init() {
		service.init();
	}
}
