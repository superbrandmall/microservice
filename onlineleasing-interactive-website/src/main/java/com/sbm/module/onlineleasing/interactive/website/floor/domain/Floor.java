package com.sbm.module.onlineleasing.interactive.website.floor.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Floor {

	@ApiModelProperty(value = "编号")
	private String code;

	@ApiModelProperty(value = "Building编号")
	private String buildingCode;

	@ApiModelProperty(value = "Floor名称")
	private String floorName;

	@ApiModelProperty(value = "Floor名称（英文）")
	private String floorNameEng;

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

	public Floor(String code, String buildingCode, String floorName, String floorNameEng, BigDecimal grossFloorArea, BigDecimal leasingArea, Integer state, String hdState, String hdCode) {
		this.code = code;
		this.buildingCode = buildingCode;
		this.floorName = floorName;
		this.floorNameEng = floorNameEng;
		this.grossFloorArea = grossFloorArea;
		this.leasingArea = leasingArea;
		this.state = state;
		this.hdState = hdState;
		this.hdCode = hdCode;
	}

	public Floor() {

	}
}
