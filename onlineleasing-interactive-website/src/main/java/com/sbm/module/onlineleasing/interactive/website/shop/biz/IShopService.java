package com.sbm.module.onlineleasing.interactive.website.shop.biz;

import com.sbm.module.onlineleasing.interactive.website.shop.domain.Shop;
import com.sbm.module.onlineleasing.interactive.website.shop.domain.ShopQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IShopService {

	Page<Shop> findAll(ShopQuery query, Pageable pageable);

}
