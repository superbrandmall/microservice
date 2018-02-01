package com.sbm.module.template.api.use.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.template.api.use.biz.IUseService;
import com.sbm.module.template.api.use.domain.Use;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CreateApiDocs
@RestController
@RequestMapping("/api/use")
public class UseController extends BaseController{

	@Autowired
	private IUseService service;

	@ApiOperation(value="根据use信息返回结果", notes="根据use信息返回结果")
	@RequestMapping(value = "/processTemplateIntoString", method = RequestMethod.POST)
	public JsonContainer<Use> processTemplateIntoString(@RequestBody Use vo) {
		service.processTemplateIntoString(vo);
		return setSuccessMessage(vo);
	}

}
