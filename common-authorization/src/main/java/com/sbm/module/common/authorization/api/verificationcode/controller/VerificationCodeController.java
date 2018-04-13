package com.sbm.module.common.authorization.api.verificationcode.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.authorization.api.verificationcode.biz.IVerificationCodeService;
import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCode;
import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCodeCheck;
import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCodeSetting;
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
@RequestMapping("/api/verificationcode")
public class VerificationCodeController extends BaseController {

	@Autowired
	private IVerificationCodeService service;

	@ApiOperation(value = "生成验证码", notes = "生成验证码")
	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	public JsonContainer<VerificationCode> generate(@RequestBody @Validated VerificationCodeSetting setting) {
		return setSuccessMessage(service.generate(setting));
	}

	@ApiOperation(value = "校验验证码", notes = "校验验证码")
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public JsonContainer check(@RequestBody @Validated VerificationCodeCheck check) {
		service.check(check);
		return setSuccessMessage();
	}

}
