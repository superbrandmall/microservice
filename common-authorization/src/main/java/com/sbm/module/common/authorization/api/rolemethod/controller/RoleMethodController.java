package com.sbm.module.common.authorization.api.rolemethod.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.authorization.api.rolemethod.biz.IRoleMethodService;
import com.sbm.module.common.authorization.api.rolemethod.domain.RoleMethod;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
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
@RequestMapping("/api/rolemethod")
public class RoleMethodController extends BaseController {

	@Autowired
	private IRoleMethodService service;

	@ApiOperation(value = "绑定角色方法", notes = "绑定角色方法")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer save(@RequestBody @Validated  List<RoleMethod> vos) {
		service.save(vos);
		return setSuccessMessage();
	}

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public JsonContainer<Page<RoleMethod>> findAll(@PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(pageable));
	}

	@ApiOperation(value = "根据角色编号，方法编号查询关联关系", notes = "根据角色编号，方法编号查询关联关系")
	@RequestMapping(value = "/findOneByRoleCodeAndMethodCode", method = RequestMethod.GET)
	public JsonContainer<RoleMethod> findOneByRoleCodeAndMethodCode(@RequestParam @NotBlank String roleCode, @RequestParam @NotBlank String methodCode) {
		return setSuccessMessage(service.findOneByRoleCodeAndMethodCode(roleCode, methodCode));
	}


}
