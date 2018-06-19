package com.sbm.module.common.authorization.api.serialcode.client;

import com.sbm.module.common.authorization.api.serialcode.domain.SerialCode;
import com.sbm.module.common.domain.JsonContainer;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/serialcode")
public interface ISerialCodeClient {

	@RequestMapping(value = "/next/{serialGroup}", method = RequestMethod.GET)
	JsonContainer<String> next(@PathVariable(value = "serialGroup") String serialGroup);

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	JsonContainer<List<SerialCode>> register(@RequestBody List<SerialCode> vos);

}
