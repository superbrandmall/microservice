package com.sbm.module.common.authorization.api.passport.domain;

import com.sbm.module.common.authorization.api.user.constant.UserConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class Register {

	@ApiModelProperty(value = "邮箱")
	@NotBlank
	private String email;

	@ApiModelProperty(value = "手机")
	@NotBlank
	private String mobile;

	@ApiModelProperty(value = "密码")
	@NotBlank
	private String password;

	@ApiModelProperty(value = "语言")
	private Integer lang;

	@ApiModelProperty(value = "境内境外")
	private Integer international = UserConstant.INTERNATIONAL_0;

	public Register() {
	}

	public Register(String email, String mobile, String password, Integer lang, Integer international) {

		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.lang = lang;
		this.international = international;
	}
}
