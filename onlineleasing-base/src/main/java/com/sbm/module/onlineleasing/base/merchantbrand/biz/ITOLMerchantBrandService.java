package com.sbm.module.onlineleasing.base.merchantbrand.biz;

import com.sbm.module.common.data.biz.IDataService;
import com.sbm.module.onlineleasing.base.merchantbrand.domain.TOLMerchantBrand;

import java.util.List;

public interface ITOLMerchantBrandService extends IDataService<TOLMerchantBrand, Integer> {

	TOLMerchantBrand findOneByMerchantCodeAndBrandCode(String merchantCode, String brandCode);

	List<TOLMerchantBrand> findAllByMerchantCode(String merchantCode);

	List<TOLMerchantBrand> findAllByBrandCode(String brandCode);

}
