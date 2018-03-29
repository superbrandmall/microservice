package com.sbm.module.onlineleasing.customer.brand.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MerchantBrand {

	@ApiModelProperty(value = "品牌编号")
	private String brandCode;

	@ApiModelProperty(value = "品牌名称")
	private String brandName;

	public MerchantBrand() {
	}

	public MerchantBrand(String brandCode, String brandName) {

		this.brandCode = brandCode;
		this.brandName = brandName;
	}
}
