package com.sbm.module.onlineleasing.interactive.website.building.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.building.biz.ITOLBuildingService;
import com.sbm.module.onlineleasing.interactive.website.building.biz.IBuildingService;
import com.sbm.module.onlineleasing.interactive.website.building.domain.Building;
import com.sbm.module.onlineleasing.interactive.website.building.domain.BuildingQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuildingServiceImpl extends CommonServiceImpl implements IBuildingService {

	@Autowired
	private ITOLBuildingService buildingService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Building> findAll(BuildingQuery query, Pageable pageable) {
		return buildingService.findAll(query.findAll(), pageable).map(e -> new Building(e.getCode(), e.getMallCode(), e.getBuildingName(),
				e.getGrossFloorArea(), e.getLeasingArea(), e.getState(), e.getHdState(), e.getHdCode()));
	}

}
