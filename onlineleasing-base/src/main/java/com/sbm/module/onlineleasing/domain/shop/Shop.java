package com.sbm.module.onlineleasing.domain.shop;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Shop extends ShopMinInfo {

	private String brandCode;

	private String buildingCode;



	private Integer shopState;



	private BigDecimal deadRent;

	private BigDecimal floatingRentalRate;

	private String shopName;

	private String buildingName;

	private String hdUuid;

	private String hdCode;

	private String hdState;

	private String vr;

	private String subType;

	public Shop(String code, String unit, String mallCode, String mallName, String floorCode, String floorName, BigDecimal area, String modality, Date contractExpireDate, String brandCode, String buildingCode, Integer shopState, BigDecimal deadRent, BigDecimal floatingRentalRate, String shopName, String buildingName, String hdUuid, String hdCode, String hdState, String vr, String subType) {
		super(code, unit, mallCode, mallName, floorCode, floorName, area, modality, contractExpireDate);
		this.brandCode = brandCode;
		this.buildingCode = buildingCode;
		this.shopState = shopState;
		this.deadRent = deadRent;
		this.floatingRentalRate = floatingRentalRate;
		this.shopName = shopName;
		this.buildingName = buildingName;
		this.hdUuid = hdUuid;
		this.hdCode = hdCode;
		this.hdState = hdState;
		this.vr = vr;
		this.subType = subType;
	}
}
