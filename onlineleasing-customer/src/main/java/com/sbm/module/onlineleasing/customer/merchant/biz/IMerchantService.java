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

	/**
	 * 通过社会统一信用代码查询
	 *
	 * @param uscc
	 * @return
	 */
	Merchant findOneByUscc(String uscc);

	/**
	 * 通过商户编号查询绑定品牌数量
	 *
	 * @param code
	 * @return
	 */
	Integer findBrandCountByMerchantCode(String code);

	/**
	 * 更新商户类型
	 *
	 * @param code
	 * @param type
	 */
	void updateType(String code, Integer type);

	/**
	 * 更新营业执照
	 *
	 * @param code
	 * @param businessLicense
	 */
	void updateBusinessLicense(String code, String businessLicense);


}
