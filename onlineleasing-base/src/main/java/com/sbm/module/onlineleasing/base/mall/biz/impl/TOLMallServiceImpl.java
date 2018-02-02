package com.sbm.module.onlineleasing.base.mall.biz.impl;

import com.sbm.module.common.data.biz.impl.JpaServiceImpl;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.onlineleasing.base.mall.repository.ITOLMallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOLMallServiceImpl extends JpaServiceImpl<TOLMall, Integer> implements ITOLMallService {

	@Autowired
	private ITOLMallRepository repository;


}
