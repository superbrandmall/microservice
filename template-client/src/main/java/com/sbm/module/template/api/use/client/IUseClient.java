package com.sbm.module.template.api.use.client;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.template.api.use.domain.Use;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "template")
@RequestMapping("/api/use")
public interface IUseClient {

	@RequestMapping("/processTemplateIntoString")
	JsonContainer<Use> processTemplateIntoString(@RequestBody Use vo);

}
