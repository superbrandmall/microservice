package com.sbm.module.partner.hd.base.floor.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.partner.hd.base.floor.biz.IFloorService;
import com.sbm.module.partner.hd.base.floor.domain.Floor;
import com.sbm.module.partner.hd.base.floor.repository.IFloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FloorServiceImpl extends DataServiceImpl<Floor, String> implements IFloorService {

	@Autowired
	private IFloorRepository repository;


}
