package com.sbm.module.common.message.api.mail.client;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.message.api.mail.domain.Mail;
import com.sbm.module.common.message.api.mail.domain.SendByTemplate;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "common-message")
@RequestMapping("/api/mail")
public interface IMailClient {

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	JsonContainer send(@RequestBody Mail vo);

	@RequestMapping(value = "/sendByTemplate", method = RequestMethod.POST)
	JsonContainer sendByTemplate(@RequestBody SendByTemplate vo);

}
