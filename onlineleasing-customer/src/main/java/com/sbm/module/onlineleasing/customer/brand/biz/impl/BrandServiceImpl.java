package com.sbm.module.onlineleasing.customer.brand.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.brand.biz.ITOLBrandService;
import com.sbm.module.onlineleasing.base.brandshopsample.biz.ITOLBrandShopSampleService;
import com.sbm.module.onlineleasing.base.merchantbrand.biz.ITOLMerchantBrandService;
import com.sbm.module.onlineleasing.customer.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.customer.brand.domain.Brand;
import com.sbm.module.onlineleasing.customer.brand.domain.BrandName;
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
	@Autowired
	private ITOLBrandShopSampleService brandShopSampleService;

	@Override
	public List<MerchantBrand> findAllByMerchantCode(String merchantCode) {
		return map(merchantBrandService.findAllByMerchantCode(merchantCode), e -> new MerchantBrand(e.getBrandCode(), brandService.findOneByCode(e.getBrandCode()).getName(), e.getBrandAuthor()));
	}

	@Override
	public Brand findOneByCode(String code) {
		return mapOneIfNotNull(brandService.findOneByCode(code), e -> new Brand(
				e.getCode(), e.getName(), e.getCity(), e.getAttribute(), e.getBrandClass(), e.getStandardArea(),
				e.getModality_1(), e.getModality_2(), e.getModality_3(), e.getTarget(), e.getAverageUnitPrice(),
				e.getLocation(), e.getShopAmount(), e.getHistory(), e.getReputation(), e.getMarketShare(), e.getRank(),
				e.getCompare(), e.getLawsuit(), e.getArrearsOfRent(), e.getTaxEvasion(), e.getQualityProblem(),
				e.getJoined(), e.getJoinOtherMall(), e.getSource(), e.getNameEng(), e.getLogo(), e.getStatus(),
				e.getHdUuid(), e.getHdCode(), e.getHdState(),
				// 品牌铺位样图
				map(brandShopSampleService.findAllByCode(code), s -> s.getShopSample())
		));
	}

	@Override
	public List<BrandName> findAllByNameContaining(String name) {
		return map(brandService.findAllByNameContaining(name), e -> new BrandName(e.getCode(), e.getName()));
	}
}
