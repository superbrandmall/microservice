package com.sbm.module.onlineleasing.base.brandshopsample.biz.impl;

import com.sbm.module.onlineleasing.base.brandshopsample.biz.ITOLBrandShopSampleService;
import com.sbm.module.onlineleasing.base.brandshopsample.domain.TOLBrandShopSample;
import com.sbm.module.onlineleasing.base.brandshopsample.repository.ITOLBrandShopSampleRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOLBrandShopSampleServiceImpl extends OLDataServiceImpl<TOLBrandShopSample, Integer> implements ITOLBrandShopSampleService {

	@Autowired
	private ITOLBrandShopSampleRepository repository;

}
