package com.sbm.module.onlineleasing.admin.brand.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.admin.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.base.brand.biz.ITOLBrandService;
import com.sbm.module.onlineleasing.base.brandshopsample.biz.ITOLBrandShopSampleService;
import com.sbm.module.onlineleasing.domain.brand.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl extends CommonServiceImpl implements IBrandService {

	@Autowired
	private ITOLBrandService brandService;
	@Autowired
	private ITOLBrandShopSampleService brandShopSampleService;

	@Override
	public Brand findOneByCode(String code) {
		return mapOneIfNotNull(brandService.findOneByCode(code), e -> new Brand(
				e.getCode(), e.getName(), e.getCity(), e.getAttribute(), e.getBrandClass(), e.getStandardArea(),
				e.getModality_1(), e.getModality_2(), e.getModality_3(), e.getTarget(), e.getAverageUnitPrice(),
				e.getLocation(), e.getShopAmount(), e.getHistory(), e.getReputation(), e.getMarketShare(), e.getRank(),
				e.getCompare(), e.getJoined(), e.getJoinOtherMall(), e.getSource(), e.getNameEng(), e.getLogo(), e.getStatus(),
				// 品牌铺位样图
				map(brandShopSampleService.findAllByCode(code), s -> s.getShopSample())
		));
	}
}
