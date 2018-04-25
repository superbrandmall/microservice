package com.sbm.module.onlineleasing.domain.reservation;

import com.sbm.module.onlineleasing.domain.brand.MerchantBrand;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Reservation extends ReservationMinInfo {

	private List<MerchantBrand> merchantBrands;

	public Reservation() {
	}

	public Reservation(String mallCode, String mallName, String floorCode, String floorName, String shopCode, String unit, BigDecimal area, String modality, String userCode, String userName, String mobile, String merchantCode, String merchantName, BigDecimal deadRent, BigDecimal floatingRentalRate, BigDecimal promotionBudget, BigDecimal maintenanceDuringDecoration, BigDecimal maintenanceAfterDecoration, BigDecimal gurantee) {
		super(mallCode, mallName, floorCode, floorName, shopCode, unit, area, modality, userCode, userName, mobile, merchantCode, merchantName, deadRent, floatingRentalRate, promotionBudget, maintenanceDuringDecoration, maintenanceAfterDecoration, gurantee);
	}
}
