package com.sbm.module.templateclient.api.use.biz;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.templateclient.api.use.domain.Use;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "TEMPLATE")
@RequestMapping("/template/api/use")
public interface IUseClient {

	@RequestMapping("/processTemplateIntoString")
	@ResponseBody
	JsonContainer<Use> processTemplateIntoString(@RequestBody Use vo);

}
