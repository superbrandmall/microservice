package com.sbm.module.mail.api.send.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.mail.api.send.biz.ISendService;
import com.sbm.module.mail.api.use.domain.Send;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CreateApiDocs
@RestController
@RequestMapping("/api/send")
public class SendController extends BaseController{

	@Autowired
	private ISendService sendService;

	@ApiOperation(value="发送消息邮件", notes="发送消息邮件")
	@RequestMapping(value = "/sendByMessage", method = RequestMethod.POST)
	@ResponseBody
	public JsonContainer sendByMessage(@RequestBody Send vo) {
		sendService.send(vo);
		return setSuccessMessage();
	}

	@ApiOperation(value="发送模板邮件", notes="发送模板邮件")
	@RequestMapping(value = "/sendByTemplate", method = RequestMethod.POST)
	@ResponseBody
	public JsonContainer sendByTemplate(@RequestBody Send vo) {
		sendService.sendByTemplate(vo);
		return setSuccessMessage();
	}

}
