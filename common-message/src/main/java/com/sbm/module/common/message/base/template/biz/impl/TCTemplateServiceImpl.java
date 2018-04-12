package com.sbm.module.common.message.base.template.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.common.message.base.template.biz.ITCTemplateService;
import com.sbm.module.common.message.base.template.domain.TCTemplate;
import com.sbm.module.common.message.base.template.repository.ITCTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TCTemplateServiceImpl extends DataServiceImpl<TCTemplate, Integer> implements ITCTemplateService {

	@Autowired
	private ITCTemplateRepository repository;


}
