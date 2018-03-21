package com.sbm.module.common.authorization.api.passport.client;

import com.sbm.module.common.authorization.api.passport.domain.Register;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.domain.JsonContainer;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/passport")
public interface IPassportClient {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	JsonContainer<User> login(@RequestParam(value = "username") @NotBlank String username, @RequestParam(value = "password") @NotBlank String password);

	@RequestMapping(value = "/updateLastLogin", method = RequestMethod.PUT)
	JsonContainer updateLastLogin(@RequestParam(value = "code") @NotBlank String code);

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	JsonContainer<User> register(@RequestBody @Validated Register register);

}
