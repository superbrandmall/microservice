package com.sbm.module.onlineleasing.admin.building.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.admin.building.biz.IBuildingService;
import com.sbm.module.onlineleasing.base.building.biz.ITOLBuildingService;
import com.sbm.module.onlineleasing.base.building.domain.TOLBuilding;
import com.sbm.module.onlineleasing.domain.building.Building;
import com.sbm.module.onlineleasing.domain.building.BuildingMaxInfo;
import com.sbm.module.onlineleasing.domain.building.BuildingQuery;
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
		return buildingService.findAll(query.findAll(), pageable).map(e -> BuildingMaxInfo.convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public BuildingMaxInfo findOneByBuildingCode(String buildingCode) {
		return mapOneIfNotNull(buildingService.findOneByCode(buildingCode), e -> {
			BuildingMaxInfo vo = BuildingMaxInfo.convert(e);
			return vo;
		});
	}

	@Override
	@Transactional
	public void save(BuildingMaxInfo buildingMaxInfo) {
		if (null != buildingMaxInfo.getBuildingCode()) {
			// 保存建筑物
			TOLBuilding building = buildingService.findOneByCode(buildingMaxInfo.getBuildingCode());
			// 描述
			building.setDescription(buildingMaxInfo.getDescription());
			buildingService.save(building);
		}
	}

}
