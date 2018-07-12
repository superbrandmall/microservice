package com.sbm.module.onlineleasing.admin.floor.biz;

import com.sbm.module.onlineleasing.domain.floor.Floor;
import com.sbm.module.onlineleasing.domain.floor.FloorMaxInfo;
import com.sbm.module.onlineleasing.domain.floor.FloorQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFloorService {

	Page<Floor> findAll(FloorQuery query, Pageable pageable);

	FloorMaxInfo findOneByFloorCode(String floorCode);

	void save(FloorMaxInfo floorMaxInfo);

}
