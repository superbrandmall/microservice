package com.sbm.module.partner.hd.base.mall.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.partner.hd.base.mall.biz.IMallService;
import com.sbm.module.partner.hd.base.mall.domain.Mall;
import com.sbm.module.partner.hd.base.mall.repository.IMallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MallServiceImpl extends DataServiceImpl<Mall, String> implements IMallService {

	@Autowired
	private IMallRepository repository;


}
