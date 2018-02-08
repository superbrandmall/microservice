package com.sbm.module.sync.hd.api.baseinfo.biz.impl;

import com.sbm.module.common.biz.impl.BusinessServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.base.building.biz.ITOLBuildingService;
import com.sbm.module.onlineleasing.base.building.domain.TOLBuilding;
import com.sbm.module.onlineleasing.base.floor.biz.ITOLFloorService;
import com.sbm.module.onlineleasing.base.floor.domain.TOLFloor;
import com.sbm.module.partner.hd.api.floor.client.IHdFloorClient;
import com.sbm.module.partner.hd.api.floor.domain.HdFloor;
import com.sbm.module.sync.hd.api.baseinfo.biz.IFloorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FloorServiceImpl extends BusinessServiceImpl<TOLFloor, HdFloor> implements IFloorService {

	@Autowired
	private IHdFloorClient hdFloorClient;
	@Autowired
	private ITOLFloorService floorService;

	@Autowired
	private ITOLBuildingService buildingService;

	private static final String MESSAGE = "building is missing, hduuid:{0}";

	@Override
	public void refresh() {
		JsonContainer<List<HdFloor>> result = hdFloorClient.findAllVo();
		List<TOLFloor> pos = findAll(result.getData());
		floorService.save(pos);
	}

	@Override
	public TOLFloor newInstance(HdFloor e) {
		TOLFloor po = floorService.findOneByHdUuid(e.getHdUuid());
		if (null == po) {
			po = new TOLFloor();
		}
		// 楼宇编号
		TOLBuilding building = buildingService.findOneByHdUuid(e.getBuildingUuid());
		if (null != building) {
			po.setBuildingCode(building.getCode());
		} else {
			log.warn(MESSAGE, e.getBuildingUuid());
		}
		// 项目名称
		po.setFloorName(e.getFloorName());
		// 建筑面积
		po.setGrossFloorArea(e.getGrossFloorArea());
		// 租赁面积
		po.setLeasingArea(e.getLeasingArea());
		// 备注
		po.setDescription(e.getDescription());
		// 海鼎uuid
		po.setHdUuid(e.getHdUuid());
		// 海鼎编码
		po.setHdCode(e.getHdCode());
		// 海鼎状态
		po.setHdState(e.getState());
		return po;
	}
}
