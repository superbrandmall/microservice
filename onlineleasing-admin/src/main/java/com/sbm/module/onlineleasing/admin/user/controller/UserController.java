package com.sbm.module.onlineleasing.admin.user.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.admin.user.biz.IUserService;
import com.sbm.module.onlineleasing.domain.user.UserSimple;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService service;

	@ApiOperation(value = "用户信息", notes = "用户信息")
	@RequestMapping(value = "/simple/{code}", method = RequestMethod.GET)
	public JsonContainer<UserSimple> findOneByCodeSimple(@PathVariable @NotBlank String code) {
		return setSuccessMessage(service.findOneByCodeSimple(code));
	}

}
