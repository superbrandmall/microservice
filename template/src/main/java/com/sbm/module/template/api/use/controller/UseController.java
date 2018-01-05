package com.sbm.module.template.api.use.controller;

import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.template.api.use.biz.IUseService;
import com.sbm.module.template.client.api.use.domain.Use;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/use")
public class UseController extends BaseController{

	@Autowired
	private IUseService useService;

	@ApiOperation(value="使用template相关服务", notes="根据use信息返回结果")
	@RequestMapping(value = "/processTemplateIntoString", method = RequestMethod.POST)
	@ResponseBody
	public JsonContainer<Use> processTemplateIntoString(@RequestBody Use vo) {
		useService.processTemplateIntoString(vo);
		return setSuccessMessage(vo);
	}
}
