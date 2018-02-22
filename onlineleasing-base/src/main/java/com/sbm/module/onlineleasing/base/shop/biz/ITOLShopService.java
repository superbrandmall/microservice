package com.sbm.module.onlineleasing.base.shop.biz;

import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

public interface ITOLShopService extends IOLDataService<TOLShop, Integer> {

	TOLShop newInstance();

	TOLShop findOneByHdUuid(String hdUuid);

}
