package com.sbm.module.onlineleasing.domain.base.coords;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Coords {

	@ApiModelProperty(value = "商铺编号")
	private String code;

	@ApiModelProperty(value = "状态")
	private Integer state;

	@ApiModelProperty(value = "商铺状态")
	private Integer shopState;

	@ApiModelProperty(value = "单元号")
	private String unit;

	@ApiModelProperty(value = "品牌名称")
	private String brandName;

	@ApiModelProperty(value = "坐标")
	private String coords;

	public Coords() {
	}

	public Coords(String code, Integer state, Integer shopState, String unit, String brandName, String coords) {

		this.code = code;
		this.state = state;
		this.shopState = shopState;
		this.unit = unit;
		this.brandName = brandName;
		this.coords = coords;
	}
}