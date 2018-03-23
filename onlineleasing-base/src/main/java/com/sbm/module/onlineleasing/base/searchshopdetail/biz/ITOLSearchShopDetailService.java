package com.sbm.module.onlineleasing.base.searchshopdetail.biz;

import com.sbm.module.onlineleasing.base.searchshopdetail.domain.TOLSearchShopDetail;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

public interface ITOLSearchShopDetailService extends IOLDataService<TOLSearchShopDetail, Integer> {

	TOLSearchShopDetail newInstance();

}
