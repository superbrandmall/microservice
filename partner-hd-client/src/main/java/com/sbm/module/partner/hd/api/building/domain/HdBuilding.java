package com.sbm.module.partner.hd.api.building.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class HdBuilding {

	@ApiModelProperty(value="UUID")
	private String hdUuid;

	@ApiModelProperty(value="楼层代码")
	private String hdCode;

	@ApiModelProperty(value="建筑物名称")
	private String buildingName;

	@ApiModelProperty(value="所属项目")
	private String mallUuid;

	@ApiModelProperty(value="状态")
	private String state;

	@ApiModelProperty(value="建筑面积")
	private BigDecimal grossFloorArea;

	@ApiModelProperty(value="租赁面积")
	private BigDecimal leasingArea;

	@ApiModelProperty(value="备注")
	private String description;

	public HdBuilding() {
	}

	public HdBuilding(String hdUuid, String hdCode, String buildingName, String mallUuid, String state, BigDecimal grossFloorArea, BigDecimal leasingArea, String description) {
		this.hdUuid = hdUuid;
		this.hdCode = hdCode;
		this.buildingName = buildingName;
		this.mallUuid = mallUuid;
		this.state = state;
		this.grossFloorArea = grossFloorArea;
		this.leasingArea = leasingArea;
		this.description = description;
	}
}
