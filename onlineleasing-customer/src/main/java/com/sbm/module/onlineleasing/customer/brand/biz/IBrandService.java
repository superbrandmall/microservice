package com.sbm.module.onlineleasing.customer.brand.biz;

import com.sbm.module.onlineleasing.customer.brand.domain.Brand;
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

	/**
	 * 通过品牌编号查询
	 *
	 * @param code
	 * @return
	 */
	Brand findOneByCode(String code);


}
