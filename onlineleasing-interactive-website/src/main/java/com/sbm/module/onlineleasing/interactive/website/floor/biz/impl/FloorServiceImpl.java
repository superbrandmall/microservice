package com.sbm.module.onlineleasing.interactive.website.floor.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.floor.biz.ITOLFloorService;
import com.sbm.module.onlineleasing.interactive.website.floor.biz.IFloorService;
import com.sbm.module.onlineleasing.interactive.website.floor.domain.Floor;
import com.sbm.module.onlineleasing.interactive.website.floor.domain.FloorQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FloorServiceImpl extends CommonServiceImpl implements IFloorService {

	@Autowired
	private ITOLFloorService floorService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Floor> findAll(FloorQuery query, Pageable pageable) {
		return floorService.findAll(query.findAll(), pageable).map(e -> new Floor(e.getCode(), e.getBuildingCode(),
				e.getDescription(), e.getDescriptionEng(), e.getGrossFloorArea(), e.getLeasingArea(),
				e.getState(), e.getHdState(), e.getHdCode()));
	}

}
