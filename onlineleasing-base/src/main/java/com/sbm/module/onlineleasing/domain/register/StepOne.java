package com.sbm.module.onlineleasing.domain.register;

import com.sbm.module.common.authorization.api.user.constant.UserConstant;
import com.sbm.module.common.authorization.api.verificationcode.domain.BaseVerificationCodeCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class StepOne extends BaseVerificationCodeCheck {

	@ApiModelProperty(value = "邮箱")
	@NotBlank
	private String email;

	@ApiModelProperty(value = "手机")
	@NotBlank
	private String mobile;

	@ApiModelProperty(value = "密码")
	//@NotBlank
	private String password;

	@ApiModelProperty(value = "语言")
	private Integer lang;

	@ApiModelProperty(value = "境内境外")
	private Integer international = UserConstant.INTERNATIONAL_0;

	@ApiModelProperty(value = "邮箱是否验证")
	private Integer emailVerified = UserConstant.VERIFIED_0;

	@ApiModelProperty(value = "手机是否验证")
	private Integer mobileVerified = UserConstant.VERIFIED_0;

}
