package com.sbm.module.onlineleasing.domain.reservation;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ReservationResult extends ReservationMinInfo {

	private String brandCode;

	private String brandName;

	private Date reserveTime;

	private Integer rentalLength;

	private Date startDate;

	private Date endDate;

	public ReservationResult(String mallCode, String mallName, String floorCode, String floorName, String shopCode, String unit, BigDecimal area, String modality, String userCode, String userName, String mobile, String merchantCode, String merchantName, BigDecimal deadRent, BigDecimal floatingRentalRate, BigDecimal promotionBudget, BigDecimal maintenanceDuringDecoration, BigDecimal maintenanceAfterDecoration, BigDecimal gurantee, String brandCode, String brandName, Date reserveTime, Integer rentalLength, Date startDate, Date endDate) {
		super(mallCode, mallName, floorCode, floorName, shopCode, unit, area, modality, userCode, userName, mobile, merchantCode, merchantName, deadRent, floatingRentalRate, promotionBudget, maintenanceDuringDecoration, maintenanceAfterDecoration, gurantee);
		this.brandCode = brandCode;
		this.brandName = brandName;
		this.reserveTime = reserveTime;
		this.rentalLength = rentalLength;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public ReservationResult() {
	}
}
