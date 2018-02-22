package com.sbm.module.onlineleasing.base.building.biz;

import com.sbm.module.onlineleasing.base.building.domain.TOLBuilding;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

public interface ITOLBuildingService extends IOLDataService<TOLBuilding, Integer> {

	TOLBuilding newInstance();

	TOLBuilding findOneByHdUuid(String hdUuid);

}
