package com.sbm.module.onlineleasing.base.modality.biz.impl;

import com.sbm.module.common.data.biz.impl.JpaServiceImpl;
import com.sbm.module.onlineleasing.base.modality.biz.ITOLModalityService;
import com.sbm.module.onlineleasing.base.modality.domain.TOLModality;
import com.sbm.module.onlineleasing.base.modality.repository.ITOLModalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOLModalityServiceImpl extends JpaServiceImpl<TOLModality, Integer> implements ITOLModalityService {

	@Autowired
	private ITOLModalityRepository repository;

}
