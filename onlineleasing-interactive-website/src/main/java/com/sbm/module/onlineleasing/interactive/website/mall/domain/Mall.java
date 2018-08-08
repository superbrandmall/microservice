package com.sbm.module.onlineleasing.interactive.website.mall.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Mall {

	@ApiModelProperty(value = "编号")
	private String code;

	@ApiModelProperty(value = "Mall名称")
	private String mallName;

	@ApiModelProperty(value = "Mall名称（英文）")
	private String mallNameEng;

	@ApiModelProperty(value = "地址")
	private String location;

	@ApiModelProperty(value = "地址（英文）")
	private String locationEng;

	@ApiModelProperty(value = "建筑面积")
	private BigDecimal grossFloorArea;

	@ApiModelProperty(value = "租赁面积")
	private BigDecimal leasingArea;

	@ApiModelProperty(value = "状态")
	private Integer state;

	@ApiModelProperty(value = "系统状态")
	private String hdState;

	@ApiModelProperty(value = "系统编号")
	private String hdCode;

	public Mall(String code, String mallName, String mallNameEng, String location, String locationEng, BigDecimal grossFloorArea, BigDecimal leasingArea, Integer state, String hdState, String hdCode) {
		this.code = code;
		this.mallName = mallName;
		this.mallNameEng = mallNameEng;
		this.location = location;
		this.locationEng = locationEng;
		this.grossFloorArea = grossFloorArea;
		this.leasingArea = leasingArea;
		this.state = state;
		this.hdState = hdState;
		this.hdCode = hdCode;
	}

	public Mall() {

	}
}
