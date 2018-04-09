package com.sbm.module.onlineleasing.base.shop.biz;

import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

import java.util.Collection;
import java.util.List;

public interface ITOLShopService extends IOLDataService<TOLShop, Integer> {

	TOLShop newInstance();

	TOLShop findOneByHdUuid(String hdUuid);

	TOLShop findOneByMallCodeAndHdCodeAndHdState(String mallCode, String hdCode, String hdState);

	List<TOLShop> findAllByFloorCodeInAndShopStateAndHdState(Collection<String> floorCodes, Integer shopState, String hdState);

	List<TOLShop> findAllByMallCodeAndShopStateAndHdState(String mallCode, Integer shopState, String hdState);

	List<TOLShop> findAllBySearchShop(Collection<String> mallCodes);

	List<TOLShop> findAllByFloorCodeInAndHdState(Collection<String> floorCodes, String hdState);

	List<TOLShop> findAllByHdState(String hdState);

}
