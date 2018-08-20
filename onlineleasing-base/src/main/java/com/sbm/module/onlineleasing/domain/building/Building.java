package com.sbm.module.onlineleasing.domain.building;

import lombok.Data;

@Data
public class Building {

	private String buildingCode;

	private String mallCode;

	private String buildingName;

	private String hdUuid;

	private String hdCode;

	private String hdState;

	public Building() {
	}

	public Building(String buildingCode, String mallCode, String buildingName, String hdUuid, String hdCode, String hdState) {
		this.buildingCode = buildingCode;
		this.mallCode = mallCode;
		this.buildingName = buildingName;
		this.hdUuid = hdUuid;
		this.hdCode = hdCode;
		this.hdState = hdState;
	}
}
