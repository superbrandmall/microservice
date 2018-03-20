package com.sbm.module.common.authorization.api.permission.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.authorization.api.permission.biz.IPermissionService;
import com.sbm.module.common.authorization.api.permission.domain.Permission;
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
@RequestMapping("/api/permission")
public class PermissionController extends BaseController {

	@Autowired
	private IPermissionService service;

	@ApiOperation(value = "校验", notes = "校验")
	@RequestMapping(value = "/valid", method = RequestMethod.POST)
	public JsonContainer<Permission> valid(@RequestBody @Validated Permission permission) {
		return setSuccessMessage(service.valid(permission));
	}

}
