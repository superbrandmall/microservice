package com.sbm.module.template.api.use.controller;

import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.template.api.use.biz.IUseService;
import com.sbm.module.templateclient.api.use.domain.Use;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/template/api/use")
public class UseController extends BaseController{

	@Autowired
	private IUseService useService;

	@RequestMapping("/processTemplateIntoString")
	@ResponseBody
	public JsonContainer processTemplateIntoString(@RequestBody Use vo) {
		useService.processTemplateIntoString(vo);
		return setSuccessMessage(vo);
	}
}
