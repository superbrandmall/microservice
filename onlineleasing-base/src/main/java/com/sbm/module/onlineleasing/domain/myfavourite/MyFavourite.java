package com.sbm.module.onlineleasing.domain.myfavourite;

import com.sbm.module.onlineleasing.domain.shop.ShopMinInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MyFavourite extends ShopMinInfo {

	public MyFavourite(String code, Integer state, String unit, String mallCode, String mallName, String floorCode, String floorName, BigDecimal area, String modality, Date contractExpireDate, Integer shopState, String subType) {
		super(code, state, unit, mallCode, mallName, floorCode, floorName, area, modality, contractExpireDate, shopState, subType);
	}
}
