package com.sbm.module.onlineleasing.admin.floor.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.admin.floor.biz.IFloorService;
import com.sbm.module.onlineleasing.base.building.biz.ITOLBuildingService;
import com.sbm.module.onlineleasing.base.building.domain.TOLBuilding;
import com.sbm.module.onlineleasing.base.floor.biz.ITOLFloorService;
import com.sbm.module.onlineleasing.base.floor.domain.TOLFloor;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.onlineleasing.domain.floor.Floor;
import com.sbm.module.onlineleasing.domain.floor.FloorMaxInfo;
import com.sbm.module.onlineleasing.domain.floor.FloorQuery;
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
	@Autowired
	private ITOLBuildingService buildingService;
	@Autowired
	private ITOLMallService mallService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Floor> findAll(FloorQuery query, Pageable pageable) {
		return floorService.findAll(query.findAll(), pageable).map(e -> FloorMaxInfo.convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public FloorMaxInfo findOneByFloorCode(String floorCode) {
		return mapOneIfNotNull(floorService.findOneByCode(floorCode), e -> {
			FloorMaxInfo vo = FloorMaxInfo.convert(e);
			TOLBuilding building = buildingService.findOneByCode(vo.getBuildingCode());
			if (null != building) {
				// 建筑物名称
				vo.setBuildingName(building.getBuildingName());
				TOLMall mall = mallService.findOneByCode(building.getMallCode());
				if (null != mall) {
					// 项目编号
					vo.setMallCode(mall.getCode());
					// 项目名称
					vo.setMallName(mall.getMallName());
				}
			}
			return vo;
		});
	}

	@Override
	@Transactional
	public void save(FloorMaxInfo floorMaxInfo) {
		if (null != floorMaxInfo.getFloorCode()) {
			// 保存楼层
			TOLFloor floor = floorService.findOneByCode(floorMaxInfo.getFloorCode());
			// 描述
			floor.setDescriptionEng(floorMaxInfo.getDescriptionEng());
			floorService.save(floor);
		}
	}

}
