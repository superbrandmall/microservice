package com.sbm.module.onlineleasing.customer.brand.biz;

import com.sbm.module.onlineleasing.customer.brand.domain.*;

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

	/**
	 * 通过品牌名称模糊查询
	 *
	 * @param name
	 * @return
	 */
	List<BrandName> findAllByNameContaining(String name);


	/**
	 * 添加新品牌
	 *
	 * @param vo
	 */
	void addNewBrand(NewBrand vo);

	/**
	 * 添加已有品牌
	 *
	 * @param vo
	 */
	void addExistingBrand(ExistingBrand vo);

}
