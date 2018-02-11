package com.sbm.module.partner.hd.api.building.biz.impl;

import com.sbm.module.common.data.biz.impl.BusinessTemplateServiceImpl;
import com.sbm.module.partner.hd.api.building.biz.IHdBuildingService;
import com.sbm.module.partner.hd.api.building.domain.HdBuilding;
import com.sbm.module.partner.hd.base.building.domain.Building;
import org.springframework.stereotype.Service;

@Service
public class HdBuildingServiceImpl extends BusinessTemplateServiceImpl<HdBuilding, Building, String> implements IHdBuildingService {

	@Override
	public HdBuilding newInstance(Building e) {
		return new HdBuilding(e.getHdUuid(), e.getHdCode(), e.getBuildingName(), e.getMallUuid(), e.getState(), e.getGrossFloorArea(), e.getLeasingArea(), e.getDescription());
	}
}
