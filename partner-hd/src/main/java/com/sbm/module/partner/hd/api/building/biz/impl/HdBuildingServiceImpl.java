package com.sbm.module.partner.hd.api.building.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.partner.hd.api.building.biz.IHdBuildingService;
import com.sbm.module.partner.hd.api.building.domain.HdBuilding;
import com.sbm.module.partner.hd.base.building.biz.IBuildingService;
import com.sbm.module.partner.hd.base.building.domain.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HdBuildingServiceImpl extends CommonServiceImpl implements IHdBuildingService {

	@Autowired
	private IBuildingService service;

	@Override
	public List<HdBuilding> findAll() {
		List<Building> pos = newArrayList(service.findAll());
		List<HdBuilding> vos = pos.stream().map(e -> new HdBuilding(e.getHdUuid(), e.getHdCode(), e.getBuildingName(), e.getMallUuid(), e.getState(), e.getGrossFloorArea(), e.getLeasingArea(), e.getDescription())).collect(Collectors.toList());
		return vos;
	}

}
