package com.sbm.module.common.authorization.api.verificationcode.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class VerificationCodeCheck {

	@ApiModelProperty(value = "键")
	@NotBlank
	private String key;

	@ApiModelProperty(value = "验证码")
	@NotBlank
	private String code;

}
