package com.sbm.module.common.authorization.api.verificationcode.client;

import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCode;
import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCodeCheck;
import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCodeSetting;
import com.sbm.module.common.domain.JsonContainer;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "common-authorization")
@RequestMapping("/api/verificationcode")
public interface IVerificationCodeClient {

	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	JsonContainer<VerificationCode> generate(@RequestBody @Validated VerificationCodeSetting setting);

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	JsonContainer check(@RequestBody @Validated VerificationCodeCheck check);

}
