package com.sbm.module.onlineleasing.base.building.biz.impl;

import com.sbm.module.common.authorization.api.serialcode.client.ISerialCodeClient;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.base.building.biz.ITOLBuildingService;
import com.sbm.module.onlineleasing.base.building.domain.TOLBuilding;
import com.sbm.module.onlineleasing.base.building.repository.ITOLBuildingRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import com.sbm.module.onlineleasing.serialcode.OnlineleasingSerialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TOLBuildingServiceImpl extends OLDataServiceImpl<TOLBuilding, Integer> implements ITOLBuildingService {

	@Autowired
	private ITOLBuildingRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TOLBuilding newInstance() {
		TOLBuilding po = new TOLBuilding();
		JsonContainer<String> result = codeClient.next(OnlineleasingSerialCode.OLBUILDING.getSerialGroup());
		checkJsonContainer(result);
		po.setCode(result.getData());
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLBuilding findOneByHdUuid(String hdUuid) {
		return repository.findOneByHdUuid(hdUuid);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLBuilding> findAllByMallCode(String mallCode) {
		return repository.findAllByMallCode(mallCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<String> findAllBuildingCodeByMallCode(String mallCode) {
		return repository.findAllBuildingCodeByMallCode(mallCode);
	}
}
