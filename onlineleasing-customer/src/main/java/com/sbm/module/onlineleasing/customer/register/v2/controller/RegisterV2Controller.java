package com.sbm.module.onlineleasing.customer.register.v2.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.register.v2.biz.IRegisterV2Service;
import com.sbm.module.onlineleasing.domain.register.StepSimple;
import com.sbm.module.onlineleasing.domain.register.StepSimpleResult;
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
@RequestMapping("/api/v2/register")
public class RegisterV2Controller extends BaseController {

	@Autowired
	private IRegisterV2Service service;

	/******************** 注册 ********************/

	@ApiOperation(value = "注册", notes = "注册")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public JsonContainer<StepSimpleResult> register(@RequestBody @Validated StepSimple vo, HttpServletResponse response) {
		return setSuccessMessage(service.register(vo, response));
	}

}
