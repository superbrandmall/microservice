package com.sbm.module.onlineleasing.customer.base.info.floor.biz;

import com.sbm.module.onlineleasing.domain.base.info.floor.FloorInfo;

public interface IFloorInfoService {

	FloorInfo findOneByMallCodeAndDescription(String mallCode, String description);

}
