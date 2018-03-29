package com.sbm.module.onlineleasing.customer.brand.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.brand.biz.ITOLBrandService;
import com.sbm.module.onlineleasing.base.merchantbrand.biz.ITOLMerchantBrandService;
import com.sbm.module.onlineleasing.customer.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.customer.brand.domain.MerchantBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl extends CommonServiceImpl implements IBrandService {

	@Autowired
	private ITOLMerchantBrandService merchantBrandService;
	@Autowired
	private ITOLBrandService brandService;

	@Override
	public List<MerchantBrand> findAllByMerchantCode(String merchantCode) {
		return map(merchantBrandService.findAllByMerchantCode(merchantCode), e -> new MerchantBrand(e.getBrandCode(), brandService.findOneByCode(e.getBrandCode()).getName()));
	}
}
