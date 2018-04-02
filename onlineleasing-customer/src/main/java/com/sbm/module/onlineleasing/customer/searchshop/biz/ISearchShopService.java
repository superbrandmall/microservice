package com.sbm.module.onlineleasing.customer.searchshop.biz;

import com.sbm.module.onlineleasing.domain.searchshop.SearchShop;
import com.sbm.module.onlineleasing.domain.searchshop.SearchShopResult;

public interface ISearchShopService {

	/**
	 * 搜索店铺结果
	 *
	 * @param searchShop
	 * @return
	 */
	SearchShopResult getDetails(SearchShop searchShop);

}
