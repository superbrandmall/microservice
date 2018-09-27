package com.sbm.module.onlineleasing.domain.brand;

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

	@ApiModelProperty(value = "品牌业态")
	private String brandModality;

	public MerchantBrand() {
	}

	public MerchantBrand(String brandCode, String brandName, String brandAuthor, String brandModality) {
		this.brandCode = brandCode;
		this.brandName = brandName;
		this.brandAuthor = brandAuthor;
		this.brandModality = brandModality;
	}

	public MerchantBrand(String brandCode, String brandName) {
		this.brandCode = brandCode;
		this.brandName = brandName;
	}
}
