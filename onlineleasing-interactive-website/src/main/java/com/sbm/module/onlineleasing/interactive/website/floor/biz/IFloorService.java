package com.sbm.module.onlineleasing.interactive.website.floor.biz;

import com.sbm.module.onlineleasing.interactive.website.floor.domain.Floor;
import com.sbm.module.onlineleasing.interactive.website.floor.domain.FloorQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFloorService {

	Page<Floor> findAll(FloorQuery query, Pageable pageable);

}
