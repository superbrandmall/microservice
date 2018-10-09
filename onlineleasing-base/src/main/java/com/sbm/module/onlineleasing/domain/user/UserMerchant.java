package com.sbm.module.onlineleasing.domain.user;

import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.authorization.api.user.domain.UserSettings;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserMerchant extends User {

	@ApiModelProperty(value = "商户编号")
	private String merchantCode;

	@ApiModelProperty(value = "商户名称")
	private String merchantName;

	@ApiModelProperty(value = "商户品牌数量")
	private Integer merchantBrandCount;

	@ApiModelProperty(value = "品牌编号")
	private String brandCode;

	@ApiModelProperty(value = "品牌名称")
	private String brandName;

	@ApiModelProperty(value = "品牌业态")
	private String brandModality;

	public UserMerchant() {
	}

	public UserMerchant(String code, String email, String mobile, String password, Date lastLogin, Integer emailVerified, Integer mobileVerified, UserSettings settings) {
		super(code, email, mobile, password, lastLogin, emailVerified, mobileVerified, settings);
	}

}
