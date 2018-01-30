package com.sbm.module.partner.hd.api.floor.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class HdFloor {

	@ApiModelProperty(value="UUID")
	private String hdUuid;

	@ApiModelProperty(value="楼层代码")
	private String hdCode;

	@ApiModelProperty(value="楼层名称")
	private String floorName;

	@ApiModelProperty(value="所属建筑物")
	private String buildingUuid;

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

	public HdFloor() {
	}

	public HdFloor(String hdUuid, String hdCode, String floorName, String buildingUuid, String mallUuid, String state, BigDecimal grossFloorArea, BigDecimal leasingArea, String description) {
		this.hdUuid = hdUuid;
		this.hdCode = hdCode;
		this.floorName = floorName;
		this.buildingUuid = buildingUuid;
		this.mallUuid = mallUuid;
		this.state = state;
		this.grossFloorArea = grossFloorArea;
		this.leasingArea = leasingArea;
		this.description = description;
	}
}
