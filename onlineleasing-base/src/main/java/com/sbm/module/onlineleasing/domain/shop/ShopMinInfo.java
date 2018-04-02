package com.sbm.module.onlineleasing.domain.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShopMinInfo {

	@ApiModelProperty(value = "铺位编号")
	private String code;

	@ApiModelProperty(value = "项目编号")
	private String mallCode;

	@ApiModelProperty(value = "项目名称")
	private String mallName;

	@ApiModelProperty(value = "楼层编号")
	private String floorCode;

	@ApiModelProperty(value = "楼层名称")
	private String floorName;

	@ApiModelProperty(value = "面积")
	private BigDecimal area;

	@ApiModelProperty(value = "业态")
	private String modality;

	@ApiModelProperty(value = "图片")
	private String firstImage;

	public ShopMinInfo() {
	}

	public ShopMinInfo(String code, String mallCode, String mallName, String floorCode, String floorName, BigDecimal area, String modality) {
		this.code = code;
		this.mallCode = mallCode;
		this.mallName = mallName;
		this.floorCode = floorCode;
		this.floorName = floorName;
		this.area = area;
		this.modality = modality;
	}
}
