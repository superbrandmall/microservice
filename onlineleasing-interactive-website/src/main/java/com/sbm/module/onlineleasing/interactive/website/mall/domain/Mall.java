package com.sbm.module.onlineleasing.interactive.website.mall.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Mall {

	private String code;

	private String mallName;

	private String mallNameEng;

	private String location;

	private String locationEng;

	private BigDecimal grossFloorArea;

	private BigDecimal leasingArea;

	private Integer state;

	private String hdState;

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
