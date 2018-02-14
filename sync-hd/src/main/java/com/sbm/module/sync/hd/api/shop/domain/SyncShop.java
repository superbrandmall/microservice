package com.sbm.module.sync.hd.api.shop.domain;

import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.base.shopengineeringimages.domain.TOLShopEngineeringImages;
import com.sbm.module.onlineleasing.base.shopengineeringspecifications.domain.TOLShopEngineeringSpecifications;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SyncShop {

	private TOLShop shop;

	private List<TOLShopEngineeringImages> engineeringImages = new ArrayList<>();

	private List<TOLShopEngineeringSpecifications> engineeringSpecifications = new ArrayList<>();

}
