package com.sbm.module.common.authorization.api.jsonwebtoken.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.authorization.api.jsonwebtoken.biz.IJSONWebTokenService;
import com.sbm.module.common.authorization.api.jsonwebtoken.domain.JSONWebToken;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/jsonwebtoken")
public class JSONWebTokenController extends BaseController {

	@Autowired
	private IJSONWebTokenService service;

	@ApiOperation(value = "生成token", notes = "生成token")
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public JsonContainer<String> token(@RequestBody @Valid JSONWebToken jsonWebToken) {
		return setSuccessMessage(service.token(jsonWebToken));
	}

	@ApiOperation(value = "校验token", notes = "校验token")
	@RequestMapping(value = "/valid", method = RequestMethod.POST)
	public JsonContainer<Boolean> valid(@RequestParam @NotBlank String login, @RequestParam @NotBlank String token) {
		return setSuccessMessage(service.valid(login, token));
	}

}
