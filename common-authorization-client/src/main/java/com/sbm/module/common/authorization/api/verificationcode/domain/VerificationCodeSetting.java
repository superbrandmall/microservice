package com.sbm.module.common.authorization.api.verificationcode.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class VerificationCodeSetting {

	@ApiModelProperty(value = "关键字")
	@NotBlank
	private String keyword;

	@ApiModelProperty(value = "长度")
	private Integer size;

	@ApiModelProperty(value = "源")
	private String sources;

}
