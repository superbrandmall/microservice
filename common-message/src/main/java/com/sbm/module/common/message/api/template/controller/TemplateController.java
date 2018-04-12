package com.sbm.module.common.message.api.template.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.message.api.template.biz.ITemplateService;
import com.sbm.module.common.message.api.template.domain.Template;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CreateApiDocs
@RestController
@RequestMapping("/api/template")
public class TemplateController extends BaseController {

	@Autowired
	private ITemplateService service;

	@ApiOperation(value = "根据模板信息返回结果", notes = "根据模板信息返回结果")
	@RequestMapping(value = "/processTemplateIntoString", method = RequestMethod.POST)
	public JsonContainer<String> processTemplateIntoString(@RequestBody Template vo) {
		return setSuccessMessage(service.processTemplateIntoString(vo));
	}

}
