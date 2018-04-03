package com.sbm.module.onlineleasing.domain.shop;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Shop extends ShopMinInfo {

	private String brandCode;

	private String buildingCode;

	private String unit;

	private Integer shopState;

	private Date contractExpireDate;

	private BigDecimal deadRent;

	private BigDecimal floatingRentalRate;

	private String shopName;

	private String buildingName;

	private String hdUuid;

	private String hdCode;

	private String hdState;

	private String vr;

	private String subType;

	public Shop(String code, String mallCode, String mallName, String floorCode, String floorName, BigDecimal area, String modality, String brandCode, String buildingCode, String unit, Integer shopState, Date contractExpireDate, BigDecimal deadRent, BigDecimal floatingRentalRate, String shopName, String buildingName, String hdUuid, String hdCode, String hdState, String vr, String subType) {
		super(code, mallCode, mallName, floorCode, floorName, area, modality);
		this.brandCode = brandCode;
		this.buildingCode = buildingCode;
		this.unit = unit;
		this.shopState = shopState;
		this.contractExpireDate = contractExpireDate;
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
