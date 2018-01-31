package com.sbm.module.partner.hd.api.mall.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.partner.hd.api.mall.biz.IHdMallService;
import com.sbm.module.partner.hd.api.mall.domain.HdMall;
import com.sbm.module.partner.hd.base.mall.biz.IMallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HdMallServiceImpl extends CommonServiceImpl implements IHdMallService {

	@Autowired
	private IMallService service;

	@Override
	public List<HdMall> findAll() {
		List<HdMall> vos = map(service.findAll(), e -> new HdMall(e.getHdUuid(), e.getHdCode(), e.getHdName(), e.getLocation(), e.getGrossFloorArea(), e.getLeasingArea(), e.getDescription(), e.getState()));
		return vos;
	}

}
