package com.sbm.module.common.authorization.api.rolemethod.client;

import com.sbm.module.common.authorization.api.rolemethod.domain.RoleMethod;
import com.sbm.module.common.domain.JsonContainer;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/rolemethod")
public interface IRoleMethodClient {

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	JsonContainer save(@RequestBody @Validated List<RoleMethod> vos);

	@RequestMapping(value = "/findOneByRoleCodeAndMethodCode", method = RequestMethod.GET)
	JsonContainer<RoleMethod> findOneByRoleCodeAndMethodCode(@RequestParam(value = "roleCode") @NotBlank String roleCode,
															 @RequestParam(value = "methodCode") @NotBlank String methodCode);
}
