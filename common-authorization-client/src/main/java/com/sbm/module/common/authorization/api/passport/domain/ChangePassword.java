package com.sbm.module.common.authorization.api.passport.domain;

import com.sbm.module.common.authorization.api.verificationcode.domain.BaseVerificationCodeCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ChangePassword extends BaseVerificationCodeCheck {

	@ApiModelProperty(value = "用户编号")
	private String code;

	@ApiModelProperty(value = "旧密码")
	private String oldPassword;

	@ApiModelProperty(value = "新密码")
	private String newPassword;

}
