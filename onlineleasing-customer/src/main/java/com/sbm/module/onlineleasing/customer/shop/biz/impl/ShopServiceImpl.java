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
import com.sbm.module.onlineleasing.domain.user.UserMerchant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
		List<TOLShopImages> shopImages = shopImagesService.findAllByCodeOrderByPosition(code);
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
		return mapOneIfNotNull(shopService.findOneByCode(code), e -> ShopMaxInfo.convert(e));
	}

	@Override
	public List<Shop> findAllBySearchShop(Collection<String> mallCodes, BigDecimal minArea, BigDecimal maxArea) {
		return map(shopService.findAllBySearchShop(mallCodes, minArea, maxArea), e -> ShopMaxInfo.convert(e));
	}

	@Override
	public ShopMaxInfo findOneByShopCodeAndUserCode(String shopCode, String userCode) {
		return mapOneIfNotNull(shopService.findOneByCode(shopCode), e -> {
			ShopMaxInfo vo = ShopMaxInfo.convert(e);
			// 品牌名称
			vo.setBrandName(mapOneIfNotNull(brandService.findOneByCode(vo.getBrandCode()), s -> s.getName()));
			// 铺位图片
			vo.setImages(map(shopImagesService.findAllByCodeOrderByPosition(vo.getCode()), s -> new ShopImages(s.getImage(), s.getPosition())));
			if (null == vo.getImages() || vo.getImages().isEmpty()) vo.setFirstImage(getShopFirstImage(vo.getCode()));
			// 工程图
			vo.setEngineeringImages(map(shopEngineeringImagesService.findAllByCode(vo.getCode()), s -> new ShopEngineeringImages(s.getAttachmentType(), s.getImage())));
			// 工程条件
			vo.setEngineeringSpecifications(map(shopEngineeringSpecificationsService.findAllByCode(vo.getCode()),
					s -> new ShopEngineeringSpecifications(s.getKeyword(), s.getName(), s.getTitle(), s.getNumber(), s.getSpec())));
			// 是否关注
			vo.setIsMyFavourite(myFavouriteService.isMyFavourite(userCode, shopCode));

			// 去除信息
			remove(userCode, vo);

			return vo;
		});
	}

	/**
	 * 去除信息
	 *
	 * @param userCode
	 * @param vo
	 */
	private void remove(String userCode, ShopMaxInfo vo) {
		// 有用户编号
		if (StringUtils.isNotBlank(userCode)) {
			// 用户商户关系
			UserMerchant userMerchant = userService.getUserMerchant(userCode);
			// 绑定商户
			if (StringUtils.isNotBlank(userMerchant.getMerchantCode())) {
				// 绑定品牌
				if (userMerchant.getMerchantBrandCount() > 0) {

				}
				// 没有绑定品牌
				else {
					removeWhenNoBrandCode(vo);
				}
			}
			// 没绑定商户
			else {
				removeWhenNoUserCodeAndNoMerchantCode(vo);
			}
		}
		// 没有用户编号
		else {
			removeWhenNoUserCodeAndNoMerchantCode(vo);
		}
	}

	/**
	 * 当没有用户编号，或者用户没有绑定商户时
	 *
	 * @param vo
	 */
	private void removeWhenNoUserCodeAndNoMerchantCode(ShopMaxInfo vo) {
		// 去除业态，品牌编号，品牌名称，工程图，工程条件
		// vo.setModality(null);
		// vo.setBrandCode(null);
		// vo.setBrandName(null);
		vo.setEngineeringImages(null);
		vo.setEngineeringSpecifications(null);
		// 去除面积，合同到期日，固定租金，浮动租金
		// vo.setArea(null);
		// vo.setContractExpireDate(null);
		vo.setDeadRent(null);
		vo.setFloatingRentalRate(null);
	}

	/**
	 * 当没有绑定品牌时
	 *
	 * @param vo
	 */
	private void removeWhenNoBrandCode(ShopMaxInfo vo) {
		// vo.setContractExpireDate(null);
		vo.setDeadRent(null);
		vo.setFloatingRentalRate(null);
	}

}
