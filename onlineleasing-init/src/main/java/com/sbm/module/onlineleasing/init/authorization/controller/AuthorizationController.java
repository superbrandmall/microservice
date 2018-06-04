package com.sbm.module.onlineleasing.init.authorization.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.init.authorization.biz.IAuthorizationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/authorization")
public class AuthorizationController extends BaseController {

	@Autowired
	private IAuthorizationService service;

	@ApiOperation(value = "初始化角色", notes = "初始化角色")
	@RequestMapping(value = "/init/role", method = RequestMethod.POST)
	public JsonContainer initRole() {
		service.initRole();
		return setSuccessMessage();
	}

	@ApiOperation(value = "初始化角色资源关系", notes = "初始化角色资源关系")
	@RequestMapping(value = "/init/rolemethod", method = RequestMethod.POST)
	public JsonContainer initRoleMethod() {
		service.initRoleMethod();
		return setSuccessMessage();
	}

	@ApiOperation(value = "初始化用户", notes = "初始化用户")
	@RequestMapping(value = "/init/user", method = RequestMethod.POST)
	public JsonContainer initUser() {
		service.initUser();
		return setSuccessMessage();
	}

	@ApiOperation(value = "初始化用户角色关系", notes = "初始化用户角色关系")
	@RequestMapping(value = "/init/userrole", method = RequestMethod.POST)
	public JsonContainer initUserRole() {
		service.initUserRole();
		return setSuccessMessage();
	}

}
