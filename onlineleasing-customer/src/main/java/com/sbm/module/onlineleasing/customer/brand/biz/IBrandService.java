package com.sbm.module.onlineleasing.customer.brand.biz;

import com.sbm.module.onlineleasing.customer.brand.domain.MerchantBrand;

import java.util.List;

public interface IBrandService {

	/**
	 * 通过商户编号查询全部品牌
	 *
	 * @param merchantCode
	 * @return
	 */
	List<MerchantBrand> findAllByMerchantCode(String merchantCode);


}
