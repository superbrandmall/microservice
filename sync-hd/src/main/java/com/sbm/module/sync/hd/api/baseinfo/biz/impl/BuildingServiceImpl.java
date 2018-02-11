package com.sbm.module.sync.hd.api.baseinfo.biz.impl;

import com.sbm.module.common.biz.impl.BusinessServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.base.building.biz.ITOLBuildingService;
import com.sbm.module.onlineleasing.base.building.domain.TOLBuilding;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.partner.hd.api.building.client.IHdBuildingClient;
import com.sbm.module.partner.hd.api.building.domain.HdBuilding;
import com.sbm.module.sync.hd.api.baseinfo.biz.IBuildingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BuildingServiceImpl extends BusinessServiceImpl<TOLBuilding, HdBuilding> implements IBuildingService {

	@Autowired
	private IHdBuildingClient hdBuildingClient;
	@Autowired
	private ITOLBuildingService buildingService;

	@Autowired
	private ITOLMallService mallService;

	private static final String MESSAGE = "mall is missing, hduuid: {}";

	@Override
	public void refresh() {
		JsonContainer<List<HdBuilding>> result = hdBuildingClient.findAll();
		List<TOLBuilding> pos = findAll(result.getData());
		buildingService.save(pos);
	}

	@Override
	public TOLBuilding newInstance(HdBuilding e) {
		TOLBuilding po = buildingService.findOneByHdUuid(e.getHdUuid());
		if (null == po) {
			po = new TOLBuilding();
		}
		// 商场编号
		TOLMall mall = mallService.findOneByHdUuid(e.getMallUuid());
		if (null != mall) {
			po.setMallCode(mall.getCode());
		} else {
			log.warn(MESSAGE, e.getMallUuid());
		}
		// 项目名称
		po.setBuildingName(e.getBuildingName());
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
