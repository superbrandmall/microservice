package com.sbm.module.common.authorization.api.method.client;

import com.sbm.module.common.authorization.api.method.domain.Method;
import com.sbm.module.common.domain.JsonContainer;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/method")
public interface IMethodClient {

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	JsonContainer<List<Method>> register(@RequestBody List<Method> vos);

	@RequestMapping(value = "/findAllByApplicationName", method = RequestMethod.GET)
	JsonContainer<List<Method>> findAllByApplicationName(@RequestParam(value = "applicationName") @NotBlank String applicationName);

}
