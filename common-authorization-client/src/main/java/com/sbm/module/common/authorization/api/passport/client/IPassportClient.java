package com.sbm.module.common.authorization.api.passport.client;

import com.sbm.module.common.authorization.api.passport.domain.Register;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.domain.JsonContainer;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/passport")
public interface IPassportClient {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	JsonContainer<User> login(@RequestParam(value = "username") @NotBlank String username, @RequestParam(value = "password") @NotBlank String password);

	@RequestMapping(value = "/login/simple", method = RequestMethod.GET)
	JsonContainer<User> loginSimple(@RequestParam(value = "username") @NotBlank String username);

	@RequestMapping(value = "/updateLastLogin", method = RequestMethod.PUT)
	JsonContainer updateLastLogin(@RequestParam(value = "code") @NotBlank String code);

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	JsonContainer<User> register(@RequestBody @Validated Register register);

	@RequestMapping(value = "/updateName", method = RequestMethod.PUT)
	JsonContainer updateName(@RequestParam(value = "code") @NotBlank String code, @RequestParam(value = "name") @NotBlank String name);

	@RequestMapping(value = "/updateNameAndIdCard", method = RequestMethod.PUT)
	JsonContainer updateNameAndIdCard(@RequestParam(value = "code") @NotBlank String code, @RequestParam(value = "name") @NotBlank String name,
									  @RequestParam(value = "idCard") @NotBlank String idCard, @RequestParam(value = "idCardType") @NotNull Integer idCardType);

	@RequestMapping(value = "/user/{userCode}", method = RequestMethod.GET)
	JsonContainer<User> findOneByCode(@PathVariable(value = "userCode") @NotBlank String userCode);

}
