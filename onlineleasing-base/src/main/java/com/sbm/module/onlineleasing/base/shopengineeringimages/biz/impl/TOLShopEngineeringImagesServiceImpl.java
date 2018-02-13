package com.sbm.module.onlineleasing.base.shopengineeringimages.biz.impl;

import com.sbm.module.onlineleasing.base.modality.repository.ITOLModalityRepository;
import com.sbm.module.onlineleasing.base.shopengineeringimages.biz.ITOLShopEngineeringImagesService;
import com.sbm.module.onlineleasing.base.shopengineeringimages.domain.TOLShopEngineeringImages;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOLShopEngineeringImagesServiceImpl extends OLDataServiceImpl<TOLShopEngineeringImages, Integer> implements ITOLShopEngineeringImagesService {

	@Autowired
	private ITOLModalityRepository repository;

}
