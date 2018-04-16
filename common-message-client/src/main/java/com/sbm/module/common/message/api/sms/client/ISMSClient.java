package com.sbm.module.common.message.api.sms.client;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.message.api.sms.domain.SMS;
import com.sbm.module.common.message.api.sms.domain.SendByTemplate;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "common-message")
@RequestMapping("/api/sms")
public interface ISMSClient {

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	JsonContainer send(@RequestBody SMS vo);

	@RequestMapping(value = "/sendByTemplate", method = RequestMethod.POST)
	JsonContainer sendByTemplate(@RequestBody SendByTemplate vo);

}
