package com.sbm.module.onlineleasing.base.merchantbrand.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.onlineleasing.base.merchantbrand.biz.ITOLMerchantBrandService;
import com.sbm.module.onlineleasing.base.merchantbrand.domain.TOLMerchantBrand;
import com.sbm.module.onlineleasing.base.merchantbrand.repository.ITOLMerchantBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TOLMerchantBrandServiceImpl extends DataServiceImpl<TOLMerchantBrand, Integer> implements ITOLMerchantBrandService {

	@Autowired
	private ITOLMerchantBrandRepository repository;

	@Override
	public List<TOLMerchantBrand> findAllByMerchantCode(String merchantCode) {
		return repository.findAllByMerchantCode(merchantCode);
	}

	@Override
	public List<TOLMerchantBrand> findAllByBrandCode(String brandCode) {
		return repository.findAllByBrandCode(brandCode);
	}
}
