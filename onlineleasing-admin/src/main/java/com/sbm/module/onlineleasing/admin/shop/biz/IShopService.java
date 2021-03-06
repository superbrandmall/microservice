package com.sbm.module.onlineleasing.admin.shop.biz;

import com.sbm.module.onlineleasing.domain.searchshop.SearchShopMinInfo;
import com.sbm.module.onlineleasing.domain.shop.Shop;
import com.sbm.module.onlineleasing.domain.shop.ShopCheck;
import com.sbm.module.onlineleasing.domain.shop.ShopMaxInfo;
import com.sbm.module.onlineleasing.domain.shop.ShopQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IShopService {

	Page<Shop> findAll(ShopQuery query, Pageable pageable);

	ShopMaxInfo findOneByShopCode(String shopCode);

	void save(ShopMaxInfo shopMaxInfo);

	Map<String, List<ShopCheck>> findAllBySearchShopAndCheck(SearchShopMinInfo searchShopMinInfo);

	void lock(String code, String operate);

}
