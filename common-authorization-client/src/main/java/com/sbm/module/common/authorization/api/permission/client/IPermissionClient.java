package com.sbm.module.common.authorization.api.permission.client;

import com.sbm.module.common.authorization.api.permission.domain.Permission;
import com.sbm.module.common.domain.JsonContainer;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/permission")
public interface IPermissionClient {

	@RequestMapping(value = "/valid")
	JsonContainer valid(@RequestBody @Validated Permission permission);

}
