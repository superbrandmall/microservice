package com.sbm.module.onlineleasing.domain.reservation;

import com.sbm.module.onlineleasing.domain.shop.ShopMinInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ReservationShopInfo extends ShopMinInfo {

	public ReservationShopInfo(String code, String unit, String mallCode, String mallName, String floorCode, String floorName, BigDecimal area, String modality, Date contractExpireDate, Integer shopState, String subType) {
		super(code, unit, mallCode, mallName, floorCode, floorName, area, modality, contractExpireDate, shopState, subType);
	}
}
