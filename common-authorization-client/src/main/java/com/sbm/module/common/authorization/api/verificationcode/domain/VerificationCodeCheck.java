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

	@ApiModelProperty(value = "关键字")
	@NotBlank
	private String keyword;

	public VerificationCodeCheck() {
	}

	public VerificationCodeCheck(String key, String code, String keyword) {
		this.key = key;
		this.code = code;
		this.keyword = keyword;
	}
}
