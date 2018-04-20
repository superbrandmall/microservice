package com.sbm.module.common.authorization.api.userrole.client;

import com.sbm.module.common.authorization.api.userrole.domain.UserRole;
import com.sbm.module.common.domain.JsonContainer;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/userrole")
public interface IUserRoleClient {

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	JsonContainer save(@RequestBody @Validated List<UserRole> vos);

}
