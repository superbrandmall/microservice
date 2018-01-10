package com.sbm.module.mail.api.send.controller;

import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.mail.api.send.biz.ISendService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class SendController extends BaseController{

	@Autowired
	private ISendService sendService;

	@ApiOperation(value="根据接收人，消息发送邮件", notes="根据接收人，消息发送邮件")
	@RequestMapping(value = "/sendByRecipientAndMessage", method = RequestMethod.POST)
	@ResponseBody
	public JsonContainer sendByRecipientAndMessage(String recipient, String message) {
		sendService.send(recipient, message);
		return setSuccessMessage();
	}

}
