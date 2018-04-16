package com.sbm.module.common.message.api.sms.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.message.api.sms.biz.ISMSService;
import com.sbm.module.common.message.api.sms.domain.SMS;
import com.sbm.module.common.message.api.sms.domain.SendByTemplate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CreateApiDocs
@RestController
@RequestMapping("/api/sms")
public class SMSController extends BaseController {

	@Autowired
	private ISMSService service;

	@ApiOperation(value = "发送消息", notes = "发送消息")
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public JsonContainer send(@RequestBody SMS vo) {
		service.send(vo);
		return setSuccessMessage();
	}

	@ApiOperation(value = "发送模板消息", notes = "发送模板消息")
	@RequestMapping(value = "/sendByTemplate", method = RequestMethod.POST)
	public JsonContainer sendByTemplate(@RequestBody SendByTemplate vo) {
		service.sendByTemplate(vo);
		return setSuccessMessage();
	}

}
