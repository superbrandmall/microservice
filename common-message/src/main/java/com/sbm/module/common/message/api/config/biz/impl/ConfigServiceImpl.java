package com.sbm.module.common.message.api.config.biz.impl;

import com.sbm.module.common.message.api.config.biz.IConfigService;
import com.sbm.module.common.message.base.template.biz.ITCTemplateService;
import com.sbm.module.common.message.base.template.domain.TCTemplate;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements IConfigService {

	@Autowired
	private Configuration configuration;

	@Autowired
	private ITCTemplateService templateService;

	public void refresh() {
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		Iterable<TCTemplate> pos = templateService.findAll();
		for (TCTemplate po : pos) {
			stringLoader.putTemplate(po.getCode(), po.getContent());
		}
		configuration.setTemplateLoader(stringLoader);
	}

}
