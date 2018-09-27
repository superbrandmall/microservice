package com.sbm.module.onlineleasing.customer.user.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.user.biz.IUserSimpleService;
import com.sbm.module.onlineleasing.domain.user.UserSimple;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

	@Autowired
	private IUserSimpleService service;

	@ApiOperation(value = "用户信息明细", notes = "用户信息明细")
	@RequestMapping(value = "/info/simple/{userCode}", method = RequestMethod.GET)
	public JsonContainer<UserSimple> getUserSimple(@PathVariable @NotBlank String userCode) {
		return setSuccessMessage(service.getUserSimple(userCode));
	}

	@ApiOperation(value = "用户信息修改", notes = "用户信息修改")
	@RequestMapping(value = "/save/simple", method = RequestMethod.PUT)
	public JsonContainer saveUserSimple(@RequestBody @Validated UserSimple vo) {
		service.saveUserSimple(vo);
		return setSuccessMessage();
	}

}
