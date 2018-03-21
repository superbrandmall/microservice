package com.sbm.module.onlineleasing.customer.register.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.login.biz.ILoginService;
import com.sbm.module.onlineleasing.customer.login.domain.Login;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/login")
public class RegisterController extends BaseController {

	@Autowired
	private ILoginService service;

	@ApiOperation(value = "登录", notes = "登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonContainer<Login> login(@RequestParam @NotBlank String username, @RequestParam @NotBlank String password, HttpServletResponse response) {
		return setSuccessMessage(service.login(username, password, response));
	}
}
