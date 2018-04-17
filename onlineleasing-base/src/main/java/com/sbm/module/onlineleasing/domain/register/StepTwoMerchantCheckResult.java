package com.sbm.module.onlineleasing.domain.register;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StepTwoMerchantCheckResult {

	@ApiModelProperty(value = "商户编号")
	private String code;

	@ApiModelProperty(value = "社会统一信用代码")
	private String uscc;

	@ApiModelProperty(value = "商户名称")
	private String merchantName;

	@ApiModelProperty(value = "营业执照")
	private String businessLicense;

	@ApiModelProperty(value = "注册资金")
	private String capital;

	@ApiModelProperty(value = "注册地址")
	private String streetAddress;

	public StepTwoMerchantCheckResult() {
	}

	public StepTwoMerchantCheckResult(String code, String uscc, String merchantName, String businessLicense, String capital, String streetAddress) {
		this.code = code;
		this.uscc = uscc;
		this.merchantName = merchantName;
		this.businessLicense = businessLicense;
		this.capital = capital;
		this.streetAddress = streetAddress;
	}
}
