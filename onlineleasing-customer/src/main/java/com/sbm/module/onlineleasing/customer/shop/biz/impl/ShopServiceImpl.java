package com.sbm.module.onlineleasing.customer.shop.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.base.shopengineeringimages.biz.ITOLShopEngineeringImagesService;
import com.sbm.module.onlineleasing.base.shopengineeringspecifications.biz.ITOLShopEngineeringSpecificationsService;
import com.sbm.module.onlineleasing.base.shopimages.biz.ITOLShopImagesService;
import com.sbm.module.onlineleasing.base.shopimages.domain.TOLShopImages;
import com.sbm.module.onlineleasing.customer.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.customer.myfavourite.biz.IMyFavouriteService;
import com.sbm.module.onlineleasing.customer.shop.biz.IShopService;
import com.sbm.module.onlineleasing.customer.user.biz.IUserService;
import com.sbm.module.onlineleasing.domain.shop.*;
import org.apache.commons.lang3.StringUtils;
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
	@Autowired
	private ITOLShopEngineeringImagesService shopEngineeringImagesService;
	@Autowired
	private ITOLShopEngineeringSpecificationsService shopEngineeringSpecificationsService;

	@Autowired
	private IUserService userService;
	@Autowired
	private IBrandService brandService;
	@Autowired
	private IMyFavouriteService myFavouriteService;

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

	private ShopMaxInfo convert(TOLShop e) {
		return new ShopMaxInfo(e.getCode(), e.getUnit(), e.getMallCode(), e.getMallName(), e.getFloorCode(), e.getFloorName(), e.getArea(), e.getModality(),
				e.getBrandCode(), e.getBuildingCode(), e.getShopState(), e.getContractExpireDate(), e.getDeadRent(), e.getFloatingRentalRate(),
				e.getShopName(), e.getBuildingName(), e.getHdUuid(), e.getHdCode(), e.getHdState(), e.getVr(), e.getSubType());
	}

	@Override
	public ShopMaxInfo findOneByShopCodeAndUserCode(String shopCode, String userCode) {
		return mapOneIfNotNull(shopService.findOneByCode(shopCode), e -> {
			ShopMaxInfo vo = convert(e);
			// 品牌名称
			vo.setBrandName(mapOneIfNotNull(brandService.findOneByCode(vo.getBrandCode()), s -> s.getName()));
			// 铺位图片
			vo.setImages(map(shopImagesService.findAllByCode(vo.getCode()), s -> new ShopImages(s.getImage(), s.getPosition())));
			if (null == vo.getImages() || vo.getImages().isEmpty()) vo.setFirstImage(getShopFirstImage(vo.getCode()));
			// 工程图
			vo.setEngineeringImages(map(shopEngineeringImagesService.findAllByCode(vo.getCode()), s -> new ShopEngineeringImages(s.getAttachmentType(), s.getImage())));
			// 工程条件
			vo.setEngineeringSpecifications(map(shopEngineeringSpecificationsService.findAllByCode(vo.getCode()),
					s -> new ShopEngineeringSpecifications(s.getKeyword(), s.getName(), s.getTitle(), s.getNumber(), s.getSpec())));
			// 去除敏感信息
			if (removable(userCode)) {
				vo.setContractExpireDate(null);
				vo.setDeadRent(null);
				vo.setFloatingRentalRate(null);
			}
			// 是否关注
			vo.setIsMyFavourite(myFavouriteService.isMyFavourite(userCode, shopCode));
			return vo;
		});
	}

	private Boolean removable(String userCode) {
		Boolean b = false;
		if (StringUtils.isNotBlank(userCode)) {
			if (0 == userService.getUserMerchant(userCode).getMerchantBrandCount()) {
				// 没有绑定品牌
				b = true;
			}
		} else {
			// 用户编码为空
			b = true;
		}
		return b;
	}

}
