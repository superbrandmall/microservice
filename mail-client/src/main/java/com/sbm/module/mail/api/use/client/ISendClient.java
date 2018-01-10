package com.sbm.module.mail.api.use.client;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.mail.api.use.domain.Send;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "mail/api")
@RequestMapping("/send")
public interface ISendClient {

	@RequestMapping("/processTemplateIntoString")
	@ResponseBody
	JsonContainer<Send> processTemplateIntoString(@RequestBody Send vo);

}
