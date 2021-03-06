package com.sbm.module.onlineleasing.customer.verify.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.verify.biz.IVerifyService;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/verify")
public class VerifyController extends BaseController {

	@Autowired
	private IVerifyService service;

	@ApiOperation(value = "验证邮件", notes = "验证邮件")
	@RequestMapping(value = "/mail", method = RequestMethod.POST)
	public JsonContainer<String> mail(@RequestParam @NotBlank String mail, @RequestHeader(required = false) String lang) {
		return setSuccessMessage(service.mail(mail, lang));
	}

	@ApiOperation(value = "验证短信", notes = "验证短信")
	@RequestMapping(value = "/sms", method = RequestMethod.POST)
	public JsonContainer<String> sms(@RequestParam @NotBlank String mobile, @RequestHeader(required = false) String lang) {
		return setSuccessMessage(service.sms(mobile, lang));
	}

}
