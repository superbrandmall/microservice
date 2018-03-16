package com.sbm.module.common.authorization.api.passport.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.authorization.api.passport.biz.IPassportService;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/passport")
public class PassportController extends BaseController {

	@Autowired
	private IPassportService service;

	@ApiOperation(value = "登录", notes = "登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonContainer<User> login(@RequestParam @NotBlank String username, @RequestParam @NotBlank String password) {
		return setSuccessMessage(service.login(username, password));
	}

	@ApiOperation(value = "修改最后登陆时间", notes = "修改最后登陆时间")
	@RequestMapping(value = "/updateLastLogin", method = RequestMethod.POST)
	public JsonContainer updateLastLogin(@RequestParam @NotBlank String code) {
		service.updateLastLogin(code);
		return setSuccessMessage();
	}

}
