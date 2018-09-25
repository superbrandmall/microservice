package com.sbm.module.onlineleasing.customer.brand.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.onlineleasing.base.brand.biz.ITOLBrandService;
import com.sbm.module.onlineleasing.base.brand.domain.TOLBrand;
import com.sbm.module.onlineleasing.base.brandshopsample.biz.ITOLBrandShopSampleService;
import com.sbm.module.onlineleasing.base.brandshopsample.domain.TOLBrandShopSample;
import com.sbm.module.onlineleasing.base.merchantbrand.biz.ITOLMerchantBrandService;
import com.sbm.module.onlineleasing.base.merchantbrand.constant.MerchantBrandConstant;
import com.sbm.module.onlineleasing.base.merchantbrand.domain.TOLMerchantBrand;
import com.sbm.module.onlineleasing.customer.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.domain.brand.*;
import com.sbm.module.onlineleasing.exception.OnlineleasingCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return map(merchantBrandService.findAllByMerchantCode(merchantCode),
				e -> new MerchantBrand(e.getBrandCode(), mapOneIfNotNull(brandService.findOneByCode(e.getBrandCode()), s -> s.getName()), e.getBrandAuthor()));
	}

	@Override
	public List<MerchantBrand> findAllWithoutBrandAuthorByMerchantCode(String merchantCode) {
		return map(merchantBrandService.findAllByMerchantCode(merchantCode),
				e -> new MerchantBrand(e.getBrandCode(), mapOneIfNotNull(brandService.findOneByCode(e.getBrandCode()), s -> s.getName())));
	}

	@Override
	public Brand findOneByCode(String code) {
		return mapOneIfNotNull(brandService.findOneByCode(code), e -> convert(e));
	}

	@Override
	public Brand findOneByName(String name) {
		return mapOneIfNotNull(brandService.findOneByName(name), e -> convert(e));
	}

	private Brand convert(TOLBrand e) {
		return new Brand(e.getCode(), e.getName(), e.getModality_1(), e.getModality_2(), e.getModality_3(), e.getNameEng(), e.getStatus(), e.getHdState(),
				e.getCity(), e.getAttribute(), e.getBrandClass(), e.getStandardArea(), e.getTarget(), e.getAverageUnitPrice(),
				e.getLocation(), e.getShopAmount(), e.getHistory(), e.getReputation(), e.getMarketShare(), e.getRank(),
				e.getCompare(), e.getJoined(), e.getJoinOtherMall(), e.getSource(), e.getLogo(),
				// 品牌铺位样图
				map(brandShopSampleService.findAllByCode(e.getCode()), s -> s.getShopSample()),
				e.getHdUuid(), e.getHdCode()
		);
	}

	@Override
	public List<BrandName> findAllByNameContaining(String name) {
		return map(brandService.findAllByNameContaining(name), e -> new BrandName(e.getCode(), e.getName()));
	}

	@Override
	@Transactional
	public void addNewBrand(NewBrand vo) {
		// 品牌
		TOLBrand po = mapOneIfNotNullThrowException(brandService.findOneByName(vo.getBrand().getName()), vo.getBrand(), e -> convert(e), new BusinessException(OnlineleasingCode.B0001, new Object[]{vo.getBrand().getName()}));
		brandService.save(po);
		// 品牌图片
		brandShopSampleService.save(map(vo.getBrand().getBrandShopSamples(), e -> new TOLBrandShopSample(po.getCode(), e)));
		// 商户品牌关系
		merchantBrandService.save(new TOLMerchantBrand(po.getCode(), vo.getMerchantCode(), MerchantBrandConstant.FIRST, vo.getBrandAuthor()));
	}

	private TOLBrand convert(Brand vo) {
		TOLBrand po = brandService.newInstance();
		po.setName(vo.getName());
		po.setCity(vo.getCity());
		po.setAttribute(vo.getAttribute());
		po.setBrandClass(vo.getBrandClass());
		po.setStandardArea(vo.getStandardArea());
		po.setModality_1(vo.getModality_1());
		po.setModality_2(vo.getModality_2());
		po.setModality_3(vo.getModality_3());
		po.setTarget(vo.getTarget());
		po.setAverageUnitPrice(vo.getAverageUnitPrice());
		po.setLocation(vo.getLocation());
		po.setShopAmount(vo.getShopAmount());
		po.setHistory(vo.getHistory());
		po.setReputation(vo.getReputation());
		po.setMarketShare(vo.getMarketShare());
		po.setRank(vo.getRank());
		po.setCompare(vo.getCompare());
		po.setJoined(vo.getJoined());
		po.setJoinOtherMall(vo.getJoinOtherMall());
		// 品牌信息来源为ol
		po.setSource(5);
		po.setNameEng(vo.getNameEng());
		po.setLogo(vo.getLogo());
		// status 暂时不放
		return po;
	}

	@Override
	@Transactional
	public void addExistingBrand(ExistingBrand vo) {
		// 商户品牌关系
		merchantBrandService.save(mapOneIfNotNullThrowException(merchantBrandService.findOneByMerchantCodeAndBrandCode(vo.getMerchantCode(), vo.getBrandCode()),
				vo, e -> new TOLMerchantBrand(vo.getBrandCode(), vo.getMerchantCode(), MerchantBrandConstant.OTHER, vo.getBrandAuthor()),
				new BusinessException(OnlineleasingCode.B0002, new Object[]{vo.getMerchantCode(), vo.getBrandCode()})));
	}

	@Override
	@Transactional
	public void save(Brand brand) {
		TOLBrand po = convert(brand);
		brandService.save(po);

		brand.setCode(po.getCode());
	}

	@Override
	@Transactional
	public void saveMerchantBrand(String merchantCode, String brandCode) {
		TOLMerchantBrand po = merchantBrandService.findOneByMerchantCodeAndBrandCode(merchantCode, brandCode);
		if (null == po) {
			merchantBrandService.save(new TOLMerchantBrand(merchantCode, brandCode, null, null));
		}
	}
}
