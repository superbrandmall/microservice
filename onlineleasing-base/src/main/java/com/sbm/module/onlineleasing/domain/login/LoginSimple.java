package com.sbm.module.onlineleasing.domain.login;

import com.sbm.module.common.authorization.api.verificationcode.domain.BaseVerificationCodeCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class LoginSimple extends BaseVerificationCodeCheck {

	@ApiModelProperty(value = "用户名")
	@NotBlank
	private String username;

}
