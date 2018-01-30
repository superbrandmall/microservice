package com.sbm.module.partner.hd.api.floor.biz.impl;

import com.google.common.collect.Lists;
import com.sbm.module.partner.hd.api.floor.biz.IHdFloorService;
import com.sbm.module.partner.hd.api.floor.domain.HdFloor;
import com.sbm.module.partner.hd.base.floor.biz.IFloorService;
import com.sbm.module.partner.hd.base.floor.domain.Floor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HdFloorServiceImpl implements IHdFloorService {

	@Autowired
	private IFloorService service;

	@Override
	public List<HdFloor> findAll() {
		Iterable<Floor> iter = service.findAll();
		List<Floor> pos = Lists.newArrayList(iter);
		List<HdFloor> vos = pos.stream().map(e -> new HdFloor(e.getHdUuid(), e.getHdCode(), e.getFloorName(), e.getBuildingUuid(), e.getMallUuid(), e.getState(), e.getGrossFloorArea(), e.getLeasingArea(), e.getDescription())).collect(Collectors.toList());
		return vos;
	}

}
