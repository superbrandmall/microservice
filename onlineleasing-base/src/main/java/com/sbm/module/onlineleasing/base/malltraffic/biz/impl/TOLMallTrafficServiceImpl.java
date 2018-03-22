package com.sbm.module.onlineleasing.base.malltraffic.biz.impl;

import com.sbm.module.onlineleasing.base.malltraffic.biz.ITOLMallTrafficService;
import com.sbm.module.onlineleasing.base.malltraffic.domain.TOLMallTraffic;
import com.sbm.module.onlineleasing.base.malltraffic.repository.ITOLMallTrafficRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOLMallTrafficServiceImpl extends OLDataServiceImpl<TOLMallTraffic, Integer> implements ITOLMallTrafficService {

	@Autowired
	private ITOLMallTrafficRepository repository;

}
