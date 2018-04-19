package com.sbm.module.common.authorization.api.passport.domain;

import com.sbm.module.common.authorization.api.verificationcode.domain.BaseVerificationCodeCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ForgetPassword extends BaseVerificationCodeCheck {

	@ApiModelProperty(value = "用户名")
	private String username;

	@ApiModelProperty(value = "新密码")
	private String newPassword;

}
