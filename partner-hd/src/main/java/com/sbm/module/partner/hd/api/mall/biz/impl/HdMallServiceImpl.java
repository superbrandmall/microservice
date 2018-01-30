package com.sbm.module.partner.hd.api.mall.biz.impl;

import com.google.common.collect.Lists;
import com.sbm.module.partner.hd.api.mall.biz.IHdMallService;
import com.sbm.module.partner.hd.api.mall.domain.HdMall;
import com.sbm.module.partner.hd.base.mall.biz.IMallService;
import com.sbm.module.partner.hd.base.mall.domain.Mall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HdMallServiceImpl implements IHdMallService {

	@Autowired
	private IMallService service;

	@Override
	public List<HdMall> findAll() {
		Iterable<Mall> iter = service.findAll();
		List<Mall> pos = Lists.newArrayList(iter);
		List<HdMall> vos = pos.stream().map(e -> new HdMall(e.getHdUuid(), e.getHdCode(), e.getHdName(), e.getLocation(), e.getGrossFloorArea(), e.getLeasingArea(), e.getDescription(), e.getState())).collect(Collectors.toList());
		return vos;
	}

}
