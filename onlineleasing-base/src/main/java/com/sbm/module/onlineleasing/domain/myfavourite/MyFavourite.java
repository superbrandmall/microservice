package com.sbm.module.onlineleasing.domain.myfavourite;

import com.sbm.module.onlineleasing.domain.shop.ShopMinInfo;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MyFavourite extends ShopMinInfo {

	public MyFavourite(String code, String mallCode, String mallName, String floorCode, String floorName, BigDecimal area, String modality) {
		super(code, mallCode, mallName, floorCode, floorName, area, modality);
	}
}
