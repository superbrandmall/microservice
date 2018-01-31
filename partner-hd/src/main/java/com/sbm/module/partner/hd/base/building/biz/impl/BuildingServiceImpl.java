package com.sbm.module.partner.hd.base.building.biz.impl;

import com.sbm.module.common.data.biz.impl.JpaServiceImpl;
import com.sbm.module.partner.hd.base.building.biz.IBuildingService;
import com.sbm.module.partner.hd.base.building.domain.Building;
import com.sbm.module.partner.hd.base.building.repository.IBuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingServiceImpl extends JpaServiceImpl<Building, String> implements IBuildingService {

	@Autowired
	private IBuildingRepository repository;


}
