package com.sbm.module.onlineleasing.customer.shop.biz;

import com.sbm.module.onlineleasing.domain.shop.Shop;
import com.sbm.module.onlineleasing.domain.shop.ShopMaxInfo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface IShopService {

	/**
	 * 获取商铺第一张图片
	 *
	 * @param shopCode
	 * @return
	 */
	String getShopFirstImage(String shopCode);

	Shop findOneByCode(String code);

	List<Shop> findAllBySearchShop(Collection<String> mallCodes, BigDecimal minArea, BigDecimal maxArea, String subType);

	ShopMaxInfo findOneByShopCodeAndUserCode(String shopCode, String userCode);

}
