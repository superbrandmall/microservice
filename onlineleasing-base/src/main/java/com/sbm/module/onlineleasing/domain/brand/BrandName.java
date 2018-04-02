package com.sbm.module.onlineleasing.domain.brand;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BrandName {

	@ApiModelProperty(value = "品牌编号")
	private String code;

	@ApiModelProperty(value = "品牌名称")
	private String name;

	public BrandName(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public BrandName() {

	}
}
