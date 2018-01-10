package com.sbm.module.template.api.config.biz.impl;

import com.sbm.module.template.api.config.biz.IConfigService;
import com.sbm.module.template.init.biz.ITemplateInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements IConfigService {


	@Autowired
	private ITemplateInitService templateInitService;


	public void refresh() {
		templateInitService.refresh();
	}

}
