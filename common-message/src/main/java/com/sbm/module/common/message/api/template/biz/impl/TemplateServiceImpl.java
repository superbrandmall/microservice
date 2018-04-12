package com.sbm.module.common.message.api.template.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.common.message.api.template.biz.ITemplateService;
import com.sbm.module.common.message.exception.MessageCode;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
public class TemplateServiceImpl extends CommonServiceImpl implements ITemplateService {

	@Autowired
	private Configuration configuration;

	@Override
	public String processTemplateIntoString(com.sbm.module.common.message.api.template.domain.Template vo) {
		String result;
		try {
			Template template = configuration.getTemplate(vo.getName());
			result = FreeMarkerTemplateUtils.processTemplateIntoString(template, vo.getModel());
		} catch (Exception e) {
			throw new BusinessException(MessageCode.T0001, new Object[]{e.getMessage()});
		}
		return result;
	}
}
