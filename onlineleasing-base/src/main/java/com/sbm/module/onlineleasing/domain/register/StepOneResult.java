package com.sbm.module.onlineleasing.domain.register;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StepOneResult {

	@ApiModelProperty(value = "用户编号")
	private String code;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "手机")
	private String mobile;

	@ApiModelProperty(value = "境内境外")
	private Integer international;

	public StepOneResult() {
	}

	public StepOneResult(String code, String email, String mobile, Integer international) {

		this.code = code;
		this.email = email;
		this.mobile = mobile;
		this.international = international;
	}
}
