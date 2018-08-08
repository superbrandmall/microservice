package com.sbm.module.onlineleasing.interactive.website.brand.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.brand.biz.ITOLBrandService;
import com.sbm.module.onlineleasing.interactive.website.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.interactive.website.brand.domain.Brand;
import com.sbm.module.onlineleasing.interactive.website.brand.domain.BrandQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BrandServiceImpl extends CommonServiceImpl implements IBrandService {

	@Autowired
	private ITOLBrandService brandService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Brand> findAll(BrandQuery query, Pageable pageable) {
		return brandService.findAll(query.findAll(), pageable).map(e -> new Brand(e.getCode(), e.getName(), e.getNameEng(), e.getModality_3(),
				e.getState(), e.getHdState(), e.getHdCode()));
	}

}
