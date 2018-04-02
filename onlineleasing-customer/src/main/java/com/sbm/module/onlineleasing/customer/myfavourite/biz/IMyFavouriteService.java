package com.sbm.module.onlineleasing.customer.myfavourite.biz;

import com.sbm.module.onlineleasing.domain.myfavourite.MyFavourite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMyFavouriteService {

	/**
	 * 关注铺位明细
	 *
	 * @param userCode
	 * @param pageable
	 * @return
	 */
	Page<MyFavourite> getDetails(String userCode, Pageable pageable);

	/**
	 * 关注
	 *
	 * @param userCode
	 * @param shopCode
	 */
	void save(String userCode, String shopCode);

	/**
	 * 取关
	 *
	 * @param userCode
	 * @param shopCode
	 */
	void delete(String userCode, String shopCode);

}
