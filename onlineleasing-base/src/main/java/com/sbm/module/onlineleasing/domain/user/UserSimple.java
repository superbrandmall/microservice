package com.sbm.module.onlineleasing.domain.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserSimple {

	@ApiModelProperty(value = "公司名称")
	private String merchantName;

	@ApiModelProperty(value = "品牌名称")
	private String brandName;

	@ApiModelProperty(value = "业态")
	private String modality;

	@ApiModelProperty(value = "官网")
	private String website;

	@ApiModelProperty(value = "文件")
	private String file;

	public UserSimple() {
	}

	public UserSimple(String merchantName, String brandName, String modality, String website, String file) {

		this.merchantName = merchantName;
		this.brandName = brandName;
		this.modality = modality;
		this.website = website;
		this.file = file;
	}
}
