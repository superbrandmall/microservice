package com.sbm.module.common.authorization.api.businesscode.client;

import com.sbm.module.common.authorization.api.businesscode.domain.BusinessCode;
import com.sbm.module.common.domain.JsonContainer;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/businesscode")
public interface IBusinessCodeClient {

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	JsonContainer<List<BusinessCode>> register(@RequestBody List<BusinessCode> vos);

	@RequestMapping(value = "/findOneByBusinessClazzAndBusinessCode", method = RequestMethod.GET)
	JsonContainer<BusinessCode> findOneByBusinessClazzAndBusinessCode(@RequestParam(value = "businessClazz") @NotBlank String businessClazz, @RequestParam(value = "businessCode") @NotBlank String businessCode);
}
