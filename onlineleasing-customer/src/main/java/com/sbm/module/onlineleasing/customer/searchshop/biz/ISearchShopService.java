package com.sbm.module.onlineleasing.customer.searchshop.biz;

import com.sbm.module.onlineleasing.domain.searchshop.SearchShop;
import com.sbm.module.onlineleasing.domain.searchshop.SearchShopResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISearchShopService {

	/**
	 * 搜索店铺结果
	 *
	 * @param searchShop
	 * @return
	 */
	SearchShopResult getDetails(SearchShop searchShop);


	Page<SearchShop> getHistories(String userCode, Pageable pageable);

}
