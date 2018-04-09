package com.sbm.module.onlineleasing.customer.myfavourite.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.onlineleasing.base.myfavourite.biz.ITOLMyFavouriteService;
import com.sbm.module.onlineleasing.base.myfavourite.domain.TOLMyFavourite;
import com.sbm.module.onlineleasing.customer.myfavourite.biz.IMyFavouriteService;
import com.sbm.module.onlineleasing.customer.shop.biz.IShopService;
import com.sbm.module.onlineleasing.domain.myfavourite.MyFavourite;
import com.sbm.module.onlineleasing.exception.OnlineleasingCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyFavouriteServiceImpl extends CommonServiceImpl implements IMyFavouriteService {

	@Autowired
	private ITOLMyFavouriteService myFavouriteService;
	@Autowired
	private IShopService shopService;

	@Override
	public Page<MyFavourite> getDetails(String userCode, Pageable pageable) {
		return myFavouriteService.findByUserCode(userCode, pageable).map(e -> {
			MyFavourite vo = mapOneIfNotNull(shopService.findOneByCode(e.getShopCode()), s -> new MyFavourite(s.getCode(), s.getMallCode(), s.getMallName(), s.getFloorCode(), s.getFloorName(), s.getArea(), s.getModality()));
			vo.setFirstImage(shopService.getShopFirstImage(vo.getCode()));
			return vo;
		});
	}

	@Override
	@Transactional
	public void save(String userCode, String shopCode) {
		myFavouriteService.save(mapOneIfNotNullThrowException(myFavouriteService.findOneByUserCodeAndShopCode(userCode, shopCode), null, e -> new TOLMyFavourite(userCode, shopCode),
				new BusinessException(OnlineleasingCode.F0001, new Object[]{userCode, shopCode})));
	}

	@Override
	@Transactional
	public void delete(String userCode, String shopCode) {
		TOLMyFavourite po = myFavouriteService.findOneByUserCodeAndShopCode(userCode, shopCode);
		myFavouriteService.delete(po);
	}

	@Override
	public Boolean isMyFavourite(String userCode, String shopCode) {
		if (null != (myFavouriteService.findOneByUserCodeAndShopCode(userCode, shopCode))) {
			return true;
		}
		return false;
	}
}
