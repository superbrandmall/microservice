package com.sbm.module.template.init.biz.impl;

import com.sbm.module.template.init.biz.ITemplateInitService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateInitServiceImpl implements ITemplateInitService {

	@Autowired
	private Configuration configuration;


	@Override
	public void init() {
		String content = "你的名字${name}";
		String mail = "message: ${message}";

		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate("content", content);
		stringLoader.putTemplate("mail", mail);
		configuration.setTemplateLoader(stringLoader);
	}

	@Override
	public void refresh() {
		init();
	}
}
