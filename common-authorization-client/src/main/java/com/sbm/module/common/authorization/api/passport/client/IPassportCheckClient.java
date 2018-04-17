package com.sbm.module.common.authorization.api.passport.client;

import com.sbm.module.common.domain.JsonContainer;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/passport/check")
public interface IPassportCheckClient {

	@RequestMapping(value = "/exist/code", method = RequestMethod.GET)
	JsonContainer existCode(@RequestParam(value = "code") @NotBlank String code);

}
