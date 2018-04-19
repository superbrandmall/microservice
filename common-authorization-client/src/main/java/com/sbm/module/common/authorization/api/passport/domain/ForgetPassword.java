package com.sbm.module.common.authorization.api.passport.domain;

import com.sbm.module.common.authorization.api.verificationcode.domain.BaseVerificationCodeCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class ForgetPassword extends BaseVerificationCodeCheck {

	@ApiModelProperty(value = "用户名")
	@NotBlank
	private String username;

	@ApiModelProperty(value = "新密码")
	@NotBlank
	private String newPassword;

}
