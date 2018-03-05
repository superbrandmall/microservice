package com.sbm.module.onlineleasing.base.floor.biz;

import com.sbm.module.onlineleasing.base.floor.domain.TOLFloor;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ITOLFloorService extends IOLDataService<TOLFloor, Integer> {

	TOLFloor newInstance();

	TOLFloor findOneByHdUuid(String hdUuid);

	List<TOLFloor> findAllByBuildingCodeInAndDescriptionAndHdState(Collection<String> buildingCodes, String description, String hdState);

	List<String> findAllDescriptionByBuildingCodeIn(Collection<String> buildingCodes);

}
