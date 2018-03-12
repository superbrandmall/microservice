package com.sbm.module.partner.hd.api.building.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.partner.hd.api.building.biz.IHdBuildingService;
import com.sbm.module.partner.hd.api.building.domain.HdBuilding;
import com.sbm.module.partner.hd.base.building.biz.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HdBuildingServiceImpl extends CommonServiceImpl implements IHdBuildingService {

	@Autowired
	private IBuildingService service;

	@Override
	public List<HdBuilding> findAll() {
		return map(service.findAll(), e -> new HdBuilding(e.getHdUuid(), e.getHdCode(), e.getBuildingName(), e.getMallUuid(), e.getState(), e.getGrossFloorArea(), e.getLeasingArea(), e.getDescription()));
	}

}
