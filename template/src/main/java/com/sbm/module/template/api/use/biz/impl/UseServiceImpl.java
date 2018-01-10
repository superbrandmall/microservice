package com.sbm.module.template.api.use.biz.impl;

import com.sbm.module.template.api.use.biz.IUseService;
import com.sbm.module.template.client.api.use.domain.Use;
import com.sbm.module.template.init.biz.ITemplateInitService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

@Service
public class UseServiceImpl implements IUseService {

	@Autowired
	private Configuration configuration;

	@Override
	public void processTemplateIntoString(Use vo) {
		try {
			Template template = configuration.getTemplate(vo.getName());
			String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, vo.getModel());
			vo.setResult(result);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

}
