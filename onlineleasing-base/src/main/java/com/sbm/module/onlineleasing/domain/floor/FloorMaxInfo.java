package com.sbm.module.onlineleasing.domain.floor;

import com.sbm.module.onlineleasing.base.floor.domain.TOLFloor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FloorMaxInfo extends Floor {

	@ApiModelProperty(value = "建筑物编号")
	private String buildingCode;

	@ApiModelProperty(value = "建筑面积")
	private BigDecimal grossFloorArea;

	@ApiModelProperty(value = "租赁面积")
	private BigDecimal leasingArea;

	@ApiModelProperty(value = "楼层名称")
	private String floorName;

	@ApiModelProperty(value = "项目编号")
	private String mallCode;

	@ApiModelProperty(value = "项目名称")
	private String mallName;

	@ApiModelProperty(value = "建筑物名称")
	private String buildingName;

	public FloorMaxInfo() {
	}

	public FloorMaxInfo(String floorCode, String description, String descriptionEng, String hdState, String buildingCode, BigDecimal grossFloorArea, BigDecimal leasingArea, String floorName) {
		super(floorCode, description, descriptionEng, hdState);
		this.buildingCode = buildingCode;
		this.grossFloorArea = grossFloorArea;
		this.leasingArea = leasingArea;
		this.floorName = floorName;
	}

	public static FloorMaxInfo convert(TOLFloor e) {
		return new FloorMaxInfo(e.getCode(), e.getDescription(), e.getDescriptionEng(),
				e.getHdState(),
				e.getBuildingCode(), e.getGrossFloorArea(), e.getLeasingArea(), e.getFloorName());
	}

}
