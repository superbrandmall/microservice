package com.sbm.module.onlineleasing.customer.merchant.biz;

import com.sbm.module.onlineleasing.domain.merchant.Merchant;

public interface IMerchantService {

	/**
	 * 通过商户编号查询
	 *
	 * @param code
	 * @return
	 */
	Merchant findOneByCode(String code);
}
