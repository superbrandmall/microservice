package com.sbm.module.onlineleasing.customer.shop.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.base.shopimages.biz.ITOLShopImagesService;
import com.sbm.module.onlineleasing.base.shopimages.domain.TOLShopImages;
import com.sbm.module.onlineleasing.customer.shop.biz.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public String getShopFirstImage(String shopCode) {
		String image;
		// 商铺第一张图片
		List<TOLShopImages> shopImages = shopImagesService.findAllByCode(shopCode);
		if (null != shopImages && !shopImages.isEmpty()) {
			image = shopImages.get(0).getImage();
		} else {
			// 如果商铺图片不存在，返回mall图片
			TOLShop shop = shopService.findOneByCode(shopCode);
			image = shop != null ? mallService.findOneByCode(shop.getMallCode()).getImg() : null;
		}
		return image;
	}

}
