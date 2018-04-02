package com.sbm.module.onlineleasing.base.myfavourite.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.onlineleasing.base.myfavourite.biz.ITOLMyFavouriteService;
import com.sbm.module.onlineleasing.base.myfavourite.domain.TOLMyFavourite;
import com.sbm.module.onlineleasing.base.myfavourite.repository.ITOLMyFavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TOLMyFavouriteServiceImpl extends DataServiceImpl<TOLMyFavourite, Integer> implements ITOLMyFavouriteService {

	@Autowired
	private ITOLMyFavouriteRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TOLMyFavourite> findByUserCode(String code, Pageable pageable) {
		return repository.findByUserCode(code, pageable);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLMyFavourite findOneByUserCodeAndShopCode(String userCode, String shopCode) {
		return repository.findOneByUserCodeAndShopCode(userCode, shopCode);
	}
}
