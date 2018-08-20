package com.sbm.module.onlineleasing.admin.building.biz;

import com.sbm.module.onlineleasing.domain.building.Building;
import com.sbm.module.onlineleasing.domain.building.BuildingMaxInfo;
import com.sbm.module.onlineleasing.domain.building.BuildingQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBuildingService {

	Page<Building> findAll(BuildingQuery query, Pageable pageable);

	BuildingMaxInfo findOneByBuildingCode(String buildingCode);

	void save(BuildingMaxInfo buildingMaxInfo);

}
