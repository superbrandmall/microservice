package com.sbm.module.common.authorization.api.rolemethod.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.authorization.api.rolemethod.biz.IRoleMethodService;
import com.sbm.module.common.authorization.api.rolemethod.domain.RoleMethod;
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
@RequestMapping("/api/rolemethod")
public class RoleMethodController extends BaseController {

	@Autowired
	private IRoleMethodService service;

	@ApiOperation(value = "绑定角色方法", notes = "绑定角色方法")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer save(@Validated @RequestBody RoleMethod vo) {
		service.save(vo);
		return setSuccessMessage();
	}

}
