package com.sbm.module.onlineleasing.interactive.website.mall.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.interactive.website.mall.biz.IMallService;
import com.sbm.module.onlineleasing.interactive.website.mall.domain.Mall;
import com.sbm.module.onlineleasing.interactive.website.mall.domain.MallQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MallServiceImpl extends CommonServiceImpl implements IMallService {

	@Autowired
	private ITOLMallService mallService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Mall> findAll(MallQuery query, Pageable pageable) {
		return mallService.findAll(query.findAll(), pageable).map(e -> new Mall(e.getCode(), e.getMallName(), e.getMallNameEng(),
				e.getLocation(), e.getLocationEng(), e.getGrossFloorArea(), e.getLeasingArea(), e.getState(), e.getHdState(), e.getHdCode()));
	}

}
