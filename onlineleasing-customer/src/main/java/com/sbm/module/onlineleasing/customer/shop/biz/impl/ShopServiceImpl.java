package com.sbm.module.onlineleasing.customer.shop.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.base.shopimages.biz.ITOLShopImagesService;
import com.sbm.module.onlineleasing.base.shopimages.domain.TOLShopImages;
import com.sbm.module.onlineleasing.customer.shop.biz.IShopService;
import com.sbm.module.onlineleasing.domain.shop.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ShopServiceImpl extends CommonServiceImpl implements IShopService {

	@Autowired
	private ITOLShopService shopService;
	@Autowired
	private ITOLShopImagesService shopImagesService;
	@Autowired
	private ITOLMallService mallService;

	@Override
	public String getShopFirstImage(String code) {
		String image;
		// 商铺第一张图片
		List<TOLShopImages> shopImages = shopImagesService.findAllByCode(code);
		if (null != shopImages && !shopImages.isEmpty()) {
			image = shopImages.get(0).getImage();
		} else {
			// 如果商铺图片不存在，返回mall图片
			TOLShop shop = shopService.findOneByCode(code);
			image = shop != null ? mallService.findOneByCode(shop.getMallCode()).getImg() : null;
		}
		return image;
	}

	@Override
	public Shop findOneByCode(String code) {
		return mapOneIfNotNull(shopService.findOneByCode(code), e -> convert(e));
	}

	@Override
	public List<Shop> findAllBySearchShop(Collection<String> mallCodes) {
		return map(shopService.findAllBySearchShop(mallCodes), e -> convert(e));
	}

	private Shop convert(TOLShop e) {
		return new Shop(e.getCode(), e.getMallCode(), e.getMallName(), e.getFloorCode(), e.getFloorName(), e.getArea(), e.getModality(),
				e.getBrandCode(), e.getBuildingCode(), e.getUnit(), e.getShopState(), e.getContractExpireDate(), e.getDeadRent(), e.getFloatingRentalRate(),
				e.getShopName(), e.getBuildingName(), e.getHdUuid(), e.getHdCode(), e.getHdState(), e.getVr(), e.getSubType());
	}

}
