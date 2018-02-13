package com.sbm.module.onlineleasing.base.shopengineeringspecifications.biz.impl;

import com.sbm.module.onlineleasing.base.shopengineeringspecifications.biz.ITOLShopEngineeringSpecificationsService;
import com.sbm.module.onlineleasing.base.shopengineeringspecifications.domain.TOLShopEngineeringSpecifications;
import com.sbm.module.onlineleasing.base.shopengineeringspecifications.repository.ITOLShopEngineeringSpecificationsRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOLShopEngineeringSpecificationsServiceImpl extends OLDataServiceImpl<TOLShopEngineeringSpecifications, Integer> implements ITOLShopEngineeringSpecificationsService {

	@Autowired
	private ITOLShopEngineeringSpecificationsRepository repository;

}
