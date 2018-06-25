package com.sbm.module.onlineleasing.domain.reservation;

import com.sbm.module.onlineleasing.domain.shop.ShopMinInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ReservationShopInfo extends ShopMinInfo {

	public ReservationShopInfo(String code, Integer state, String unit, String mallCode, String floorCode, BigDecimal area, String modality, Date contractExpireDate, Integer shopState, String subType) {
		super(code, state, unit, mallCode, floorCode, area, modality, contractExpireDate, shopState, subType);
	}
}
