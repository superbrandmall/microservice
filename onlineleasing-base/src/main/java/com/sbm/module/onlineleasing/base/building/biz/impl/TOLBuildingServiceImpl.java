package com.sbm.module.onlineleasing.base.building.biz.impl;

import com.sbm.module.onlineleasing.base.building.biz.ITOLBuildingService;
import com.sbm.module.onlineleasing.base.building.domain.TOLBuilding;
import com.sbm.module.onlineleasing.base.building.repository.ITOLBuildingRepository;
import com.sbm.module.onlineleasing.base.serialcode.constant.SerialCodeConstant;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TOLBuildingServiceImpl extends OLDataServiceImpl<TOLBuilding, Integer> implements ITOLBuildingService {

	@Autowired
	private ITOLBuildingRepository repository;

	@Override
	public TOLBuilding newInstance() {
		TOLBuilding po = new TOLBuilding();
		po.setCode(serialCodeService.next(SerialCodeConstant.OLBUILDING).getNext());
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLBuilding findOneByHdUuid(String hdUuid) {
		return repository.findOneByHdUuid(hdUuid);
	}

}
