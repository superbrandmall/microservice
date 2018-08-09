package com.sbm.module.onlineleasing.interactive.website.shopimages.biz;

import com.sbm.module.onlineleasing.interactive.website.shopimages.domain.ShopImages;
import com.sbm.module.onlineleasing.interactive.website.shopimages.domain.ShopImagesQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IShopImagesService {

	Page<ShopImages> findAll(ShopImagesQuery query, Pageable pageable);

}
