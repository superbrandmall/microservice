package com.sbm.module.onlineleasing.interactive.website.modality.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.modality.biz.ITOLModalityService;
import com.sbm.module.onlineleasing.interactive.website.modality.biz.IModalityService;
import com.sbm.module.onlineleasing.interactive.website.modality.domain.Modality;
import com.sbm.module.onlineleasing.interactive.website.modality.domain.ModalityQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModalityServiceImpl extends CommonServiceImpl implements IModalityService {

	@Autowired
	private ITOLModalityService mallService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Modality> findAll(ModalityQuery query, Pageable pageable) {
		return mallService.findAll(query.findAll(), pageable).map(e -> new Modality(e.getCode(), e.getName(), e.getRemark(), e.getLv()));
	}

}
