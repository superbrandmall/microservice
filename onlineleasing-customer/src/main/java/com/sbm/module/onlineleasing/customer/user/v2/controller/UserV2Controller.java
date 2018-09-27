package com.sbm.module.onlineleasing.customer.user.v2.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.user.v2.biz.IUserInfoService;
import com.sbm.module.onlineleasing.domain.user.UserMerchant;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/v2/user")
public class UserV2Controller extends BaseController {

	@Autowired
	private IUserInfoService service;

	@ApiOperation(value = "用户信息明细", notes = "用户信息明细")
	@RequestMapping(value = "/info/{userCode}", method = RequestMethod.GET)
	public JsonContainer<UserMerchant> getUserSimple(@PathVariable @NotBlank String userCode) {
		return setSuccessMessage(service.getUserMerchant(userCode));
	}

	@ApiOperation(value = "用户信息修改", notes = "用户信息修改")
	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public JsonContainer saveUserSimple(@RequestBody @Validated UserMerchant vo) {
		service.saveUserMerchant(vo);
		return setSuccessMessage();
	}

}
