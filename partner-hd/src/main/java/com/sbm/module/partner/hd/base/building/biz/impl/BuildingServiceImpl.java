package com.sbm.module.partner.hd.base.building.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.partner.hd.base.building.biz.IBuildingService;
import com.sbm.module.partner.hd.base.building.domain.Building;
import org.springframework.stereotype.Service;

@Service
public class BuildingServiceImpl extends DataServiceImpl<Building, String> implements IBuildingService {

}
