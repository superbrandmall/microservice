package com.sbm.module.onlineleasing.domain.reservation;

import com.sbm.module.onlineleasing.domain.brand.MerchantBrand;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ReservationMinInfo {

	private String mallCode;

	private String mallName;

	private String floorCode;

	private String floorName;

	private String shopCode;

	private String unit;

	private BigDecimal area;

	private String modality;

	private Date contractExpireDate;


	private String userCode;

	private String userName;

	private String mobile;

	private String merchantCode;

	private String merchantName;


	private BigDecimal deadRent;

	private BigDecimal floatingRentalRate;

	private BigDecimal promotionBudget;

	private BigDecimal maintenanceDuringDecoration;

	private BigDecimal maintenanceAfterDecoration;

	private BigDecimal gurantee;


	@ApiModelProperty(value = "图片")
	private String firstImage;


	public ReservationMinInfo() {

	}

	public ReservationMinInfo(String mallCode, String mallName, String floorCode, String floorName, String shopCode, String unit, BigDecimal area, String modality, String userCode, String userName, String mobile, String merchantCode, String merchantName, BigDecimal deadRent, BigDecimal floatingRentalRate, BigDecimal promotionBudget, BigDecimal maintenanceDuringDecoration, BigDecimal maintenanceAfterDecoration, BigDecimal gurantee) {
		this.mallCode = mallCode;
		this.mallName = mallName;
		this.floorCode = floorCode;
		this.floorName = floorName;
		this.shopCode = shopCode;
		this.unit = unit;
		this.area = area;
		this.modality = modality;
		this.userCode = userCode;
		this.userName = userName;
		this.mobile = mobile;
		this.merchantCode = merchantCode;
		this.merchantName = merchantName;
		this.deadRent = deadRent;
		this.floatingRentalRate = floatingRentalRate;
		this.promotionBudget = promotionBudget;
		this.maintenanceDuringDecoration = maintenanceDuringDecoration;
		this.maintenanceAfterDecoration = maintenanceAfterDecoration;
		this.gurantee = gurantee;
	}
}
