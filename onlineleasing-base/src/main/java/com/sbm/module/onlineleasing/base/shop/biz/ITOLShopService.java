package com.sbm.module.onlineleasing.base.shop.biz;

import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

import java.util.Collection;
import java.util.List;

public interface ITOLShopService extends IOLDataService<TOLShop, Integer> {

	TOLShop newInstance();

	TOLShop findOneByHdUuid(String hdUuid);

	List<TOLShop> findAllByFloorCodeInAndShopStateAndHdState(Collection<String> floorCodes, Integer shopState, String hdState);

}
