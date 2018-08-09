package com.sbm.module.onlineleasing.interactive.website.shopimages.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.shopimages.biz.ITOLShopImagesService;
import com.sbm.module.onlineleasing.interactive.website.shopimages.biz.IShopImagesService;
import com.sbm.module.onlineleasing.interactive.website.shopimages.domain.ShopImages;
import com.sbm.module.onlineleasing.interactive.website.shopimages.domain.ShopImagesQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopImagesServiceImpl extends CommonServiceImpl implements IShopImagesService {

	@Autowired
	private ITOLShopImagesService shopImagesService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<ShopImages> findAll(ShopImagesQuery query, Pageable pageable) {
		return shopImagesService.findAll(query.findAll(), pageable).map(e -> new ShopImages(e.getCode(), e.getImage(), e.getPosition(), e.getState()));
	}

}
