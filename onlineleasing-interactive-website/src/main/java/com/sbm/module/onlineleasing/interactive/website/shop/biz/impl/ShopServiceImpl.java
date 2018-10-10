package com.sbm.module.onlineleasing.interactive.website.shop.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.interactive.website.shop.biz.IShopService;
import com.sbm.module.onlineleasing.interactive.website.shop.domain.Shop;
import com.sbm.module.onlineleasing.interactive.website.shop.domain.ShopQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopServiceImpl extends CommonServiceImpl implements IShopService {

	@Autowired
	private ITOLShopService shopService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Shop> findAll(ShopQuery query, Pageable pageable) {
		return shopService.findAll(query.findAll(), pageable).map(e -> new Shop(e.getCode(), e.getBrandCode(), e.getMallCode(), e.getBuildingCode(), e.getFloorCode(),
				e.getUnit(), e.getShopName(), e.getArea(), e.getState(), e.getHdState(), e.getHdCode(), e.getShopState(), e.getContractExpireDate(), e.getSubType()));
	}

}
