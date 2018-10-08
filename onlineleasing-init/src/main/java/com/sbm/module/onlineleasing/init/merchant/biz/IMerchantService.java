package com.sbm.module.onlineleasing.init.merchant.biz;

import com.sbm.module.onlineleasing.init.merchant.domain.MerchantCheck;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IMerchantService {

	/**
	 * 初始化
	 *
	 * @return
	 */
	List<MerchantCheck> init();

	void initDownload(HttpServletResponse response);

}
