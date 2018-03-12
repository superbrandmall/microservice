package com.sbm.module.common.authorization.api.method.client;

import com.sbm.module.common.authorization.api.method.domain.Method;
import com.sbm.module.common.domain.JsonContainer;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/method")
public interface IMethodClient {

	@RequestMapping(value = "/register")
	JsonContainer<List<Method>> register(@RequestBody List<Method> vos);

}
