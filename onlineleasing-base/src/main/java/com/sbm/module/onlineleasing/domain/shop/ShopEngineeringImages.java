package com.sbm.module.onlineleasing.domain.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShopEngineeringImages {

	@ApiModelProperty(value = "类型")
	private String attachmentType;

	@ApiModelProperty(value = "图片地址")
	private String image;

	public ShopEngineeringImages(String attachmentType, String image) {
		this.attachmentType = attachmentType;
		this.image = image;
	}
}
