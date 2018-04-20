package com.sbm.module.onlineleasing.domain.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserMerchant {

	@ApiModelProperty(value = "用户编号")
	private String userCode;

	@ApiModelProperty(value = "商户编号")
	private String merchantCode;

	@ApiModelProperty(value = "商户名称")
	private String merchantName;

	@ApiModelProperty(value = "商户品牌数量")
	private Integer merchantBrandCount;

	public UserMerchant(String userCode, String merchantCode, String merchantName, Integer merchantBrandCount) {
		this.userCode = userCode;
		this.merchantCode = merchantCode;
		this.merchantName = merchantName;
		this.merchantBrandCount = merchantBrandCount;
	}

	public UserMerchant() {

	}
}
