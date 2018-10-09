package com.sbm.module.onlineleasing.init.merchant.biz;

import com.sbm.module.onlineleasing.init.merchant.domain.MerchantCheck;

import java.util.List;

public interface IMerchantService {

	/**
	 * 初始化
	 *
	 * @param flag
	 * @return
	 */
	List<MerchantCheck> init(Boolean flag);

	/**
	 * 初始化Merchant并获取下载
	 *
	 * @return
	 */
	String initDownload();

}
