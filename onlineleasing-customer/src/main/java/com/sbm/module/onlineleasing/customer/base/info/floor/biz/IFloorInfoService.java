package com.sbm.module.onlineleasing.customer.base.info.floor.biz;

import com.sbm.module.onlineleasing.domain.base.info.floor.FloorInfo;
import com.sbm.module.onlineleasing.domain.base.info.floor.FloorMinInfo;

import java.util.List;

public interface IFloorInfoService {

	FloorInfo findOneByMallCodeAndDescription(String mallCode, String description);

	List<FloorMinInfo> findAll();
}
