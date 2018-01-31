package com.sbm.module.partner.hd.api.mall.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.partner.hd.api.mall.biz.IHdMallService;
import com.sbm.module.partner.hd.api.mall.domain.HdMall;
import com.sbm.module.partner.hd.base.mall.domain.Mall;
import org.springframework.stereotype.Service;

@Service
public class HdMallServiceImpl extends DataServiceImpl<HdMall, Mall, String> implements IHdMallService {

	@Override
	public HdMall newInstance(Mall e) {
		return new HdMall(e.getHdUuid(), e.getHdCode(), e.getHdName(), e.getLocation(), e.getGrossFloorArea(), e.getLeasingArea(), e.getDescription(), e.getState());
	}
}
