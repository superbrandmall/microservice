package com.sbm.module.onlineleasing.base.modality.biz.impl;

import com.sbm.module.onlineleasing.base.modality.biz.ITOLModalityService;
import com.sbm.module.onlineleasing.base.modality.domain.TOLModality;
import com.sbm.module.onlineleasing.base.modality.repository.ITOLModalityRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TOLModalityServiceImpl extends OLDataServiceImpl<TOLModality, Integer> implements ITOLModalityService {

	@Autowired
	private ITOLModalityRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLModality findOneByHdUuid(String hdUuid) {
		return repository.findOneByHdUuid(hdUuid);
	}

	@Override
	public List<TOLModality> findAllByLvAndCodeStartingWith(String lv, String code) {
		return repository.findAllByLvAndCodeStartingWith(lv, code);
	}
}
