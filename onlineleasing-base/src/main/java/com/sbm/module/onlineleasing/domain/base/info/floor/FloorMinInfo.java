package com.sbm.module.onlineleasing.domain.base.info.floor;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FloorMinInfo {

	@ApiModelProperty(value = "楼层编号")
	private String floorCode;


	@ApiModelProperty(value = "描述")
	private String description;

	@ApiModelProperty(value = "描述（英文）")
	private String descriptionEng;

	public FloorMinInfo() {
	}

	public FloorMinInfo(String floorCode, String description, String descriptionEng) {
		this.floorCode = floorCode;
		this.description = description;
		this.descriptionEng = descriptionEng;
	}
}
