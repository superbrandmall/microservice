package com.sbm.module.onlineleasing.base.shopimages.biz.impl;

import com.sbm.module.onlineleasing.base.shopimages.biz.ITOLShopImagesService;
import com.sbm.module.onlineleasing.base.shopimages.domain.TOLShopImages;
import com.sbm.module.onlineleasing.base.shopimages.repository.ITOLShopImagesRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TOLShopImagesServiceImpl extends OLDataServiceImpl<TOLShopImages, Integer> implements ITOLShopImagesService {

	@Autowired
	private ITOLShopImagesRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLShopImages> findAllByCodeOrderByPosition(String code) {
		return repository.findAllByCodeOrderByPosition(code);
	}
}
