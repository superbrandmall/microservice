package com.sbm.module.onlineleasing.interactive.website.building.biz;

import com.sbm.module.onlineleasing.interactive.website.building.domain.Building;
import com.sbm.module.onlineleasing.interactive.website.building.domain.BuildingQuery;
import com.sbm.module.onlineleasing.interactive.website.mall.domain.Mall;
import com.sbm.module.onlineleasing.interactive.website.mall.domain.MallQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBuildingService {

	Page<Building> findAll(BuildingQuery query, Pageable pageable);

}
