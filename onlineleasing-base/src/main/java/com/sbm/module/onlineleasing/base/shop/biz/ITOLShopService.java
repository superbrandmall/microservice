package com.sbm.module.onlineleasing.base.shop.biz;

import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface ITOLShopService extends IOLDataService<TOLShop, Integer> {

	TOLShop newInstance();

	TOLShop findOneByHdUuid(String hdUuid);

	TOLShop findOneByMallCodeAndHdCodeAndHdState(String mallCode, String hdCode, String hdState);

	List<TOLShop> findAllByFloorCodeInAndShopStateInAndHdState(Collection<String> floorCodes, Collection<Integer> shopStates, String hdState);

	List<TOLShop> findAllByMallCodeAndShopStateInAndHdState(String mallCode, Collection<Integer> shopStates, String hdState);

	List<TOLShop> findAllBySearchShop(Collection<String> mallCodes, BigDecimal minArea, BigDecimal maxArea, String subType);

	List<TOLShop> findAllByFloorCodeInAndHdState(Collection<String> floorCodes, String hdState);

	List<TOLShop> findAllByHdState(String hdState);

}
