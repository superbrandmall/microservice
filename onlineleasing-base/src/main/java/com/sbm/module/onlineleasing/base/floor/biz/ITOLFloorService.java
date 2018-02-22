package com.sbm.module.onlineleasing.base.floor.biz;

import com.sbm.module.onlineleasing.base.floor.domain.TOLFloor;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

public interface ITOLFloorService extends IOLDataService<TOLFloor, Integer> {

	TOLFloor newInstance();

	TOLFloor findOneByHdUuid(String hdUuid);

}
