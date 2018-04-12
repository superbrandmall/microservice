package com.sbm.module.common.message.api.template.biz;


import com.sbm.module.common.message.api.template.domain.Template;

public interface ITemplateService {

	String processTemplateIntoString(Template vo);

}
