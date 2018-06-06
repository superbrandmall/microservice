package com.sbm.module.onlineleasing.domain.reservation;

import com.sbm.module.common.authorization.api.verificationcode.domain.BaseVerificationCodeCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class ReservationUserInfo extends BaseVerificationCodeCheck {

	@NotBlank
	private String userCode;

	private String userName;

	private String mobile;

	private String email;

	private String merchantCode;

	private String merchantName;

	private String brandCode;

	private String brandName;

	private String brandModality;

	@ApiModelProperty(value = "邮箱是否验证")
	private Integer emailVerified;

	@ApiModelProperty(value = "手机是否验证")
	private Integer mobileVerified;

	public ReservationUserInfo() {
	}

	public ReservationUserInfo(String userCode, String userName, String mobile, String email, String merchantCode, String merchantName, String brandCode, String brandName, String brandModality) {

		this.userCode = userCode;
		this.userName = userName;
		this.mobile = mobile;
		this.email = email;
		this.merchantCode = merchantCode;
		this.merchantName = merchantName;
		this.brandCode = brandCode;
		this.brandName = brandName;
		this.brandModality = brandModality;
	}
}
