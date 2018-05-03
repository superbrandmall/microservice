package com.sbm.module.onlineleasing.domain.myfavourite;

import com.sbm.module.onlineleasing.domain.shop.ShopMinInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MyFavourite extends ShopMinInfo {

	public MyFavourite(String code, String unit, String mallCode, String mallName, String floorCode, String floorName, BigDecimal area, String modality, Date contractExpireDate) {
		super(code, unit, mallCode, mallName, floorCode, floorName, area, modality, contractExpireDate);
	}
}
