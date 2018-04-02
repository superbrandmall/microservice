package com.sbm.module.onlineleasing.customer.searchshop.biz;

import com.sbm.module.onlineleasing.domain.searchshop.SearchShop;
import com.sbm.module.onlineleasing.domain.searchshop.ShopScore;

import java.util.List;

public interface IShopScoreService {


	/**
	 * 计算商铺评分结果
	 *
	 * @param searchShop
	 * @return
	 */
	List<ShopScore> calShopScore(SearchShop searchShop);

}
