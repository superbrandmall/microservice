package com.sbm.module.onlineleasing.domain.building;

import com.sbm.module.onlineleasing.base.building.domain.TOLBuilding;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuildingMaxInfo extends Building {

	private BigDecimal grossFloorArea;

	private BigDecimal leasingArea;

	private String description;

	public BuildingMaxInfo() {
	}

	public BuildingMaxInfo(String code, String mallCode, String buildingName, String hdUuid, String hdCode, String hdState, BigDecimal grossFloorArea, BigDecimal leasingArea, String description) {
		super(code, mallCode, buildingName, hdUuid, hdCode, hdState);
		this.grossFloorArea = grossFloorArea;
		this.leasingArea = leasingArea;
		this.description = description;
	}

	public static BuildingMaxInfo convert(TOLBuilding e) {
		return new BuildingMaxInfo(e.getCode(), e.getMallCode(), e.getBuildingName(), e.getHdUuid(), e.getHdCode(), e.getHdState(),
				e.getGrossFloorArea(), e.getLeasingArea(), e.getDescription());
	}

}
