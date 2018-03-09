package com.sbm.module.common.authorization.api.serialcode.client;

import com.sbm.module.common.domain.JsonContainer;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/serialcode")
public interface ISerialCodeClient {

	@RequestMapping(value = "/next/{serialGroup}")
	JsonContainer<String> next(@PathVariable(value = "serialGroup") String serialGroup);

}
