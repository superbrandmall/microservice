package com.sbm.module.onlineleasing.customer.register.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.register.biz.IRegisterService;
import com.sbm.module.onlineleasing.domain.register.StepOne;
import com.sbm.module.onlineleasing.domain.register.StepOneResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/register")
public class RegisterController extends BaseController {

	@Autowired
	private IRegisterService service;

	@ApiOperation(value = "登录", notes = "登录")
	@RequestMapping(value = "/step/one", method = RequestMethod.POST)
	public JsonContainer<StepOneResult> stepOne(@RequestBody @Validated StepOne vo, HttpServletResponse response) {
		return setSuccessMessage(service.stepOne(vo, response));
	}
}
