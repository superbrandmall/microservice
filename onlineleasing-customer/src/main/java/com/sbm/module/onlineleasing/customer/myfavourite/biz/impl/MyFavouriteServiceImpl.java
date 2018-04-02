package com.sbm.module.onlineleasing.customer.myfavourite.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.myfavourite.biz.ITOLMyFavouriteService;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.customer.myfavourite.biz.IMyFavouriteService;
import com.sbm.module.onlineleasing.customer.shop.biz.IShopService;
import com.sbm.module.onlineleasing.domain.myfavourite.MyFavourite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MyFavouriteServiceImpl extends CommonServiceImpl implements IMyFavouriteService {

	@Autowired
	private ITOLShopService tolShopService;
	@Autowired
	private ITOLMyFavouriteService myFavouriteService;
	@Autowired
	private IShopService shopService;

	@Override
	public Page<MyFavourite> getDetails(String userCode, Pageable pageable) {
		return myFavouriteService.findByUserCode(userCode, pageable).map(e -> {
			TOLShop shop = tolShopService.findOneByCode(e.getShopCode());
			MyFavourite vo = new MyFavourite(shop.getCode(), shop.getMallCode(), shop.getMallName(), shop.getFloorCode(), shop.getFloorName(), shop.getArea(), shop.getModality());
			vo.setFirstImage(shopService.getShopFirstImage(vo.getShopCode()));
			return vo;
		});
	}
}
