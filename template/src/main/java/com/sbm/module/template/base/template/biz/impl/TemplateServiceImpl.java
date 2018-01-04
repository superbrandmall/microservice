package com.sbm.module.template.base.template.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.template.base.template.biz.ITemplateService;
import com.sbm.module.template.base.template.domain.Template;
import com.sbm.module.template.base.template.repository.ITemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl extends DataServiceImpl<Template, Integer> implements ITemplateService {

	@Autowired
	private ITemplateRepository templateRepository;


}
