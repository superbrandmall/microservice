package com.sbm.module.common.authorization.api.businesscode.client;

import com.sbm.module.common.authorization.api.businesscode.domain.BusinessCode;
import com.sbm.module.common.domain.JsonContainer;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/businesscode")
public interface IBusinessCodeClient {

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	JsonContainer<List<BusinessCode>> register(@RequestBody List<BusinessCode> vos);

}
