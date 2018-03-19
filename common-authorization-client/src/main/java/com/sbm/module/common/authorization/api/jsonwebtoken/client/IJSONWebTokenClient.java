package com.sbm.module.common.authorization.api.jsonwebtoken.client;

import com.sbm.module.common.authorization.api.jsonwebtoken.domain.JSONWebToken;
import com.sbm.module.common.domain.JsonContainer;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/jsonwebtoken")
public interface IJSONWebTokenClient {

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	JsonContainer<String> token(@RequestBody @Validated JSONWebToken jsonWebToken);

	@RequestMapping(value = "/valid", method = RequestMethod.POST)
	JsonContainer valid(@RequestParam(value = "login") String login, @RequestParam("token") String token);

}
