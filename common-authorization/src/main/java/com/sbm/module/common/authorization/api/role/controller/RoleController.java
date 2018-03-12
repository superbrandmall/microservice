package com.sbm.module.common.authorization.api.role.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.authorization.api.api.role.domain.Role;
import com.sbm.module.common.authorization.api.method.domain.Method;
import com.sbm.module.common.authorization.api.role.biz.IRoleService;
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
@RequestMapping("/api/role")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService service;

	@ApiOperation(value = "添加或修改角色", notes = "添加或修改角色")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer<Method> save(@RequestBody Role vo) {
		service.save(vo);
		return setSuccessMessage();
	}

}
