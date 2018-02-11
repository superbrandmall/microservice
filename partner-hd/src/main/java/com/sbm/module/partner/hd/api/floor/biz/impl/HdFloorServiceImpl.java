package com.sbm.module.partner.hd.api.floor.biz.impl;

import com.sbm.module.common.data.biz.impl.BusinessTemplateServiceImpl;
import com.sbm.module.partner.hd.api.floor.biz.IHdFloorService;
import com.sbm.module.partner.hd.api.floor.domain.HdFloor;
import com.sbm.module.partner.hd.base.floor.domain.Floor;
import org.springframework.stereotype.Service;

@Service
public class HdFloorServiceImpl extends BusinessTemplateServiceImpl<HdFloor, Floor, String> implements IHdFloorService {

	@Override
	public HdFloor newInstance(Floor e) {
		return new HdFloor(e.getHdUuid(), e.getHdCode(), e.getFloorName(), e.getBuildingUuid(), e.getMallUuid(), e.getState(), e.getGrossFloorArea(), e.getLeasingArea(), e.getDescription());
	}
}
