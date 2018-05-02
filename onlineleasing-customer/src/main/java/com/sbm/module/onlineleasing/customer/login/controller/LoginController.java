package com.sbm.module.onlineleasing.customer.login.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.login.biz.ILoginService;
import com.sbm.module.onlineleasing.domain.login.LoginResult;
import com.sbm.module.onlineleasing.domain.login.LoginSimple;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/login")
public class LoginController extends BaseController {

	@Autowired
	private ILoginService service;

	@ApiOperation(value = "登录", notes = "登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonContainer<LoginResult> login(@RequestParam @NotBlank String username, @RequestParam @NotBlank String password, HttpServletResponse response) {
		return setSuccessMessage(service.login(username, password, response));
	}

	@ApiOperation(value = "登录简单版", notes = "登录简单版")
	@RequestMapping(value = "/login/simple", method = RequestMethod.POST)
	public JsonContainer<LoginResult> loginSimple(@RequestBody @Validated LoginSimple vo, HttpServletResponse response) {
		return setSuccessMessage(service.loginSimple(vo, response));
	}

}
