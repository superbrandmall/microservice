package com.sbm.module.onlineleasing.domain.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShopImages {

	@ApiModelProperty(value = "图片地址")
	private String image;

	@ApiModelProperty(value = "位置")
	private Integer position;

	public ShopImages(String image, Integer position) {
		this.image = image;
		this.position = position;
	}
}
