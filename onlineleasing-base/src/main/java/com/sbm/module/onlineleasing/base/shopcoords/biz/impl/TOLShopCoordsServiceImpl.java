package com.sbm.module.onlineleasing.base.shopcoords.biz.impl;

import com.sbm.module.onlineleasing.base.shopcoords.biz.ITOLShopCoordsService;
import com.sbm.module.onlineleasing.base.shopcoords.domain.TOLShopCoords;
import com.sbm.module.onlineleasing.base.shopcoords.repository.ITOLShopCoordsRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOLShopCoordsServiceImpl extends OLDataServiceImpl<TOLShopCoords, Integer> implements ITOLShopCoordsService {

	@Autowired
	private ITOLShopCoordsRepository repository;


}
