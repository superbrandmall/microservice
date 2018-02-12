package com.sbm.module.sync.hd.api.baseinfo.biz.impl;

import com.sbm.module.sync.hd.api.baseinfo.biz.IBaseInfoService;
import com.sbm.module.sync.hd.api.baseinfo.biz.IBuildingService;
import com.sbm.module.sync.hd.api.baseinfo.biz.IFloorService;
import com.sbm.module.sync.hd.api.baseinfo.biz.IMallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BaseInfoServiceImpl implements IBaseInfoService {

	@Autowired
	private IMallService mallService;
	@Autowired
	private IBuildingService buildingService;
	@Autowired
	private IFloorService floorService;

	@Override
	@Scheduled(cron = "${sync.cron.baseinfo}")
	public void refresh() {
		mallService.refresh();
		buildingService.refresh();
		floorService.refresh();
	}
}
