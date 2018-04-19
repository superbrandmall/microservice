package com.sbm.module.common.authorization.api.passport.domain;

import com.sbm.module.common.authorization.api.verificationcode.domain.BaseVerificationCodeCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class ChangePassword extends BaseVerificationCodeCheck {

	@ApiModelProperty(value = "用户编号")
	@NotBlank
	private String code;

	@ApiModelProperty(value = "旧密码")
	@NotBlank
	private String oldPassword;

	@ApiModelProperty(value = "新密码")
	@NotBlank
	private String newPassword;

}
