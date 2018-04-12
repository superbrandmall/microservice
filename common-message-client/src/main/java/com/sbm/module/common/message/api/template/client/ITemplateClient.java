package com.sbm.module.common.message.api.template.client;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.message.api.template.domain.Template;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "common-message")
@RequestMapping("/api/template")
public interface ITemplateClient {

	@RequestMapping("/processTemplateIntoString")
	JsonContainer<String> processTemplateIntoString(@RequestBody Template vo);

}
