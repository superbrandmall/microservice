package com.sbm.module.common.authorization.api.verificationcode.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class BaseVerificationCodeCheck {

	@ApiModelProperty(value = "验证码")
	@Valid
	@NotNull
	private VerificationCodeCheck verificationCodeCheck;

}
