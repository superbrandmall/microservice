package com.sbm.module.common.authorization.api.method.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.authorization.api.method.biz.IMethodService;
import com.sbm.module.common.authorization.api.method.domain.Method;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/method")
public class MethodController extends BaseController {

	@Autowired
	private IMethodService service;

	@ApiOperation(value = "注册方法", notes = "注册方法")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public JsonContainer<Method> register(@RequestBody Method vo) {
		service.register(vo);
		return setSuccessMessage(vo);
	}

}
