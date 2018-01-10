package com.sbm.module.template.init.biz.impl;

import com.sbm.module.template.base.template.biz.ITemplateService;
import com.sbm.module.template.base.template.domain.Template;
import com.sbm.module.template.init.biz.ITemplateInitService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateInitServiceImpl implements ITemplateInitService {

	@Autowired
	private Configuration configuration;

	@Autowired
	private ITemplateService templateService;

	@Override
	public void init() {
		StringTemplateLoader stringLoader = new StringTemplateLoader();

		Iterable<Template> pos = templateService.findAll();
		for (Template po : pos) {
			stringLoader.putTemplate(po.getCode(), po.getContent());
		}
		configuration.setTemplateLoader(stringLoader);
	}

	@Override
	public void refresh() {
		init();
	}
}
