package com.sbm.module.common.authorization.api.userrole.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.authorization.api.userrole.biz.IUserRoleService;
import com.sbm.module.common.authorization.api.userrole.domain.UserRole;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/userrole")
public class UserRoleController extends BaseController {

	@Autowired
	private IUserRoleService service;

	@ApiOperation(value = "绑定角色方法", notes = "绑定角色方法")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer save(@Validated @RequestBody List<UserRole> vos) {
		service.save(vos);
		return setSuccessMessage();
	}

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public JsonContainer<Page<UserRole>> findAll(@PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(pageable));
	}

	@ApiOperation(value = "查询用户全部角色", notes = "查询用户全部角色")
	@RequestMapping(value = "/findAllByUserCode/{userCode}", method = RequestMethod.GET)
	public JsonContainer<List<UserRole>> findAllByUserCode(@PathVariable String userCode) {
		return setSuccessMessage(service.findAllByUserCode(userCode));
	}

}
