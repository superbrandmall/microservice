package com.sbm.module.onlineleasing.base.merchantbrand.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.onlineleasing.base.merchantbrand.biz.ITOLMerchantBrandService;
import com.sbm.module.onlineleasing.base.merchantbrand.domain.TOLMerchantBrand;
import com.sbm.module.onlineleasing.base.merchantbrand.repository.ITOLMerchantBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TOLMerchantBrandServiceImpl extends DataServiceImpl<TOLMerchantBrand, Integer> implements ITOLMerchantBrandService {

	@Autowired
	private ITOLMerchantBrandRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLMerchantBrand findOneByMerchantCodeAndBrandCode(String merchantCode, String brandCode) {
		return repository.findOneByMerchantCodeAndBrandCode(merchantCode, brandCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLMerchantBrand> findAllByMerchantCode(String merchantCode) {
		return repository.findAllByMerchantCode(merchantCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLMerchantBrand> findAllByBrandCode(String brandCode) {
		return repository.findAllByBrandCode(brandCode);
	}
}
