package com.sbm.module.onlineleasing.domain.shop;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Shop extends ShopMinInfo {

	private String brandCode;

	private String buildingCode;


	private BigDecimal deadRent;

	private BigDecimal floatingRentalRate;

	private String shopName;

	private String buildingName;

	private String hdUuid;

	private String hdCode;

	private String hdState;

	private String vr;

	public Shop(String code, String unit, String mallCode, String mallName, String floorCode, String floorName, BigDecimal area, String modality, Date contractExpireDate, Integer shopState, String subType, String brandCode, String buildingCode, BigDecimal deadRent, BigDecimal floatingRentalRate, String shopName, String buildingName, String hdUuid, String hdCode, String hdState, String vr) {
		super(code, unit, mallCode, mallName, floorCode, floorName, area, modality, contractExpireDate, shopState, subType);
		this.brandCode = brandCode;
		this.buildingCode = buildingCode;
		this.deadRent = deadRent;
		this.floatingRentalRate = floatingRentalRate;
		this.shopName = shopName;
		this.buildingName = buildingName;
		this.hdUuid = hdUuid;
		this.hdCode = hdCode;
		this.hdState = hdState;
		this.vr = vr;
	}
}
