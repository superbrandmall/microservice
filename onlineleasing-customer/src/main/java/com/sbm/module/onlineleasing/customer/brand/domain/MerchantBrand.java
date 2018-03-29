package com.sbm.module.onlineleasing.customer.brand.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MerchantBrand {

	@ApiModelProperty(value = "品牌编号")
	private String brandCode;

	@ApiModelProperty(value = "品牌名称")
	private String brandName;

	@ApiModelProperty(value = "品牌授权书")
	private String brandAuthor;

	public MerchantBrand() {
	}

	public MerchantBrand(String brandCode, String brandName, String brandAuthor) {
		this.brandCode = brandCode;
		this.brandName = brandName;
		this.brandAuthor = brandAuthor;
	}
}
