package com.sbm.module.onlineleasing.interactive.website.building.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "Building信息")
public class Building {

	@ApiModelProperty(value = "编号")
	private String code;

	@ApiModelProperty(value = "Mall编号")
	private String mallCode;

	@ApiModelProperty(value = "Building名称")
	private String buildingName;

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

	public Building(String code, String mallCode, String buildingName, BigDecimal grossFloorArea, BigDecimal leasingArea, Integer state, String hdState, String hdCode) {
		this.code = code;
		this.mallCode = mallCode;
		this.buildingName = buildingName;
		this.grossFloorArea = grossFloorArea;
		this.leasingArea = leasingArea;
		this.state = state;
		this.hdState = hdState;
		this.hdCode = hdCode;
	}

	public Building() {

	}
}
