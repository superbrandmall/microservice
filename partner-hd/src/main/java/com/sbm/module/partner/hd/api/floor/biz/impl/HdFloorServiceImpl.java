package com.sbm.module.partner.hd.api.floor.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.partner.hd.api.floor.biz.IHdFloorService;
import com.sbm.module.partner.hd.api.floor.domain.HdFloor;
import com.sbm.module.partner.hd.base.floor.biz.IFloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HdFloorServiceImpl extends CommonServiceImpl implements IHdFloorService {

	@Autowired
	private IFloorService service;

	@Override
	public List<HdFloor> findAll() {
		List<HdFloor> vos = map(service.findAll(), e -> new HdFloor(e.getHdUuid(), e.getHdCode(), e.getFloorName(), e.getBuildingUuid(), e.getMallUuid(), e.getState(), e.getGrossFloorArea(), e.getLeasingArea(), e.getDescription()));
		return vos;
	}

}
