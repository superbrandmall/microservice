package com.sbm.module.common.message.api.mail.client;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.message.api.mail.domain.Mail;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "common-message")
@RequestMapping("/api/mail")
public interface IMailClient {

	@RequestMapping("/sendByRecipientAndMessage")
	JsonContainer<Mail> sendByRecipientAndMessage(@RequestBody Mail vo);

}
