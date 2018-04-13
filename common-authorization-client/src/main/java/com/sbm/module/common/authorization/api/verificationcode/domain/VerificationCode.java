package com.sbm.module.common.authorization.api.verificationcode.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VerificationCode {

	@ApiModelProperty(value = "键")
	private String key;

	@ApiModelProperty(value = "验证码")
	private String code;

	public VerificationCode() {
	}

	public VerificationCode(String key, String code) {
		this.key = key;
		this.code = code;
	}
}
