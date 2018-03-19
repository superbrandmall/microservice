package com.sbm.module.common.authorization.api.passport.client;

import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.domain.JsonContainer;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/passport")
public interface IPassportClient {

	@RequestMapping(value = "/login")
	JsonContainer<User> login(@RequestParam("username") @NotBlank String username, @RequestParam("password") @NotBlank String password);

	@RequestMapping(value = "/updateLastLogin")
	JsonContainer updateLastLogin(@RequestParam("code") @NotBlank String code);

}
