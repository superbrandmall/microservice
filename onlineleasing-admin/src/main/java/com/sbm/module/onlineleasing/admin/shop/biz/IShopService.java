package com.sbm.module.onlineleasing.admin.shop.biz;

import com.sbm.module.onlineleasing.domain.shop.Shop;
import com.sbm.module.onlineleasing.domain.shop.ShopQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IShopService {

	Page<Shop> findAll(ShopQuery query, Pageable pageable);

}
