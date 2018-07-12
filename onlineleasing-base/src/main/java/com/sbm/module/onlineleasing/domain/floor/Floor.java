package com.sbm.module.onlineleasing.domain.floor;

import com.sbm.module.onlineleasing.domain.base.info.floor.FloorMinInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Floor extends FloorMinInfo {

	@ApiModelProperty(value = "海鼎状态")
	private String hdState;

	public Floor(String floorCode, String description, String descriptionEng, String hdState) {
		super(floorCode, description, descriptionEng);
		this.hdState = hdState;
	}

	public Floor() {

	}
}
