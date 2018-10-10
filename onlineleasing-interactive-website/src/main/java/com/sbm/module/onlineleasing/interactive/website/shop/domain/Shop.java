package com.sbm.module.onlineleasing.interactive.website.shop.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Shop {

	@ApiModelProperty(value = "编号")
	private String code;

	@ApiModelProperty(value = "Brand编号")
	private String brandCode;

	@ApiModelProperty(value = "Mall编号")
	private String mallCode;

	@ApiModelProperty(value = "Building编号")
	private String buildingCode;

	@ApiModelProperty(value = "Floor编号")
	private String floorCode;

	@ApiModelProperty(value = "单元号")
	private String unit;

	@ApiModelProperty(value = "门牌号")
	private String shopName;

	@ApiModelProperty(value = "面积")
	private BigDecimal area;

	@ApiModelProperty(value = "状态")
	private Integer state;

	@ApiModelProperty(value = "系统状态")
	private String hdState;

	@ApiModelProperty(value = "系统编号")
	private String hdCode;

	@ApiModelProperty(value = "铺位状态")
	private Integer shopState;

	@ApiModelProperty(value = "合同到期日")
	private Date contractExpireDate;

	public Shop(String code, String brandCode, String mallCode, String buildingCode, String floorCode, String unit, String shopName, BigDecimal area, Integer state, String hdState, String hdCode, Integer shopState, Date contractExpireDate) {
		this.code = code;
		this.brandCode = brandCode;
		this.mallCode = mallCode;
		this.buildingCode = buildingCode;
		this.floorCode = floorCode;
		this.unit = unit;
		this.shopName = shopName;
		this.area = area;
		this.state = state;
		this.hdState = hdState;
		this.hdCode = hdCode;
		this.shopState = shopState;
		this.contractExpireDate = contractExpireDate;
	}

	public Shop() {

	}
}
