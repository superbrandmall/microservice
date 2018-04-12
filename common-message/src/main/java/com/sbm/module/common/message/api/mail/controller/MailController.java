package com.sbm.module.common.message.api.mail.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.message.api.mail.biz.IMailService;
import com.sbm.module.common.message.api.mail.domain.Mail;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CreateApiDocs
@RestController
@RequestMapping("/api/Mail")
public class MailController extends BaseController {

	@Autowired
	private IMailService service;

	@ApiOperation(value = "发送消息邮件", notes = "发送消息邮件")
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public JsonContainer send(@RequestBody Mail vo) {
		service.send(vo);
		return setSuccessMessage();
	}

	@ApiOperation(value = "发送模板邮件", notes = "发送模板邮件")
	@RequestMapping(value = "/sendByTemplate", method = RequestMethod.POST)
	public JsonContainer sendByTemplate(@RequestBody Mail vo) {
		service.sendByTemplate(vo);
		return setSuccessMessage();
	}

}
