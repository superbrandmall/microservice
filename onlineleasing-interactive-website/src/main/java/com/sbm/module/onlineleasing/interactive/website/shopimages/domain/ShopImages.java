package com.sbm.module.onlineleasing.interactive.website.shopimages.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShopImages {

	@ApiModelProperty(value = "Shop编号")
	private String shopCode;

	@ApiModelProperty(value = "地址")
	private String url;

	@ApiModelProperty(value = "序号")
	private Integer order;

	@ApiModelProperty(value = "状态")
	private Integer state;

	public ShopImages(String shopCode, String url, Integer order, Integer state) {
		this.shopCode = shopCode;
		this.url = url;
		this.order = order;
		this.state = state;
	}

	public ShopImages() {

	}
}
