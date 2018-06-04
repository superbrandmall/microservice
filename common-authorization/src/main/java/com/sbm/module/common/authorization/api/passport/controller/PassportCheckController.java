package com.sbm.module.common.authorization.api.passport.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.authorization.api.passport.biz.IPassportCheckService;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/passport/check")
public class PassportCheckController extends BaseController {

	@Autowired
	private IPassportCheckService service;

	/********** 检查（不存在） **********/

	@ApiOperation(value = "手机号不存在", notes = "手机号不存在")
	@RequestMapping(value = "/isNotExist/mobile", method = RequestMethod.GET)
	public JsonContainer isNotExistMobile(@RequestParam @NotBlank String mobile) {
		service.isNotExistMobile(mobile);
		return setSuccessMessage();
	}

	@ApiOperation(value = "邮箱不存在", notes = "邮箱不存在")
	@RequestMapping(value = "/isNotExist/email", method = RequestMethod.GET)
	public JsonContainer isNotExistEmail(@RequestParam @NotBlank String email) {
		service.isNotExistEmail(email);
		return setSuccessMessage();
	}

	@ApiOperation(value = "证件不存在", notes = "证件不存在")
	@RequestMapping(value = "/isNotExist/idCard", method = RequestMethod.GET)
	public JsonContainer isNotExistIdCard(@RequestParam @NotBlank String idCard) {
		service.isNotExistIdCard(idCard);
		return setSuccessMessage();
	}

	/********** 检查（存在） **********/

	@ApiOperation(value = "手机号存在", notes = "手机号存在")
	@RequestMapping(value = "/exist/mobile", method = RequestMethod.GET)
	public JsonContainer existMobile(@RequestParam @NotBlank String mobile) {
		service.existMobile(mobile);
		return setSuccessMessage();
	}

	@ApiOperation(value = "邮箱存在", notes = "邮箱存在")
	@RequestMapping(value = "/exist/email", method = RequestMethod.GET)
	public JsonContainer existEmail(@RequestParam @NotBlank String email) {
		service.existEmail(email);
		return setSuccessMessage();
	}

	@ApiOperation(value = "证件存在", notes = "证件存在")
	@RequestMapping(value = "/exist/idCard", method = RequestMethod.GET)
	public JsonContainer existIdCard(@RequestParam @NotBlank String idCard) {
		service.existIdCard(idCard);
		return setSuccessMessage();
	}

	@ApiOperation(value = "用户编号存在", notes = "用户编号存在")
	@RequestMapping(value = "/exist/code", method = RequestMethod.GET)
	public JsonContainer existCode(@RequestParam @NotBlank String code) {
		service.existCode(code);
		return setSuccessMessage();
	}

	@ApiOperation(value = "用户拥有指定角色", notes = "用户拥有指定角色")
	@RequestMapping(value = "/exist/userrole", method = RequestMethod.GET)
	public JsonContainer existUserRole(@RequestParam @NotBlank String userCode, @RequestParam @NotBlank String role) {
		service.existUserRole(userCode, role);
		return setSuccessMessage();
	}
}
