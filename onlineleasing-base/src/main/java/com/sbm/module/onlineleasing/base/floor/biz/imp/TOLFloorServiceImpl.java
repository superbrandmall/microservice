package com.sbm.module.onlineleasing.base.floor.biz.imp;

import com.sbm.module.common.authorization.api.serialcode.client.ISerialCodeClient;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.base.floor.biz.ITOLFloorService;
import com.sbm.module.onlineleasing.base.floor.domain.TOLFloor;
import com.sbm.module.onlineleasing.base.floor.repository.ITOLFloorRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import com.sbm.module.onlineleasing.serialcode.OnlineleasingSerialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class TOLFloorServiceImpl extends OLDataServiceImpl<TOLFloor, Integer> implements ITOLFloorService {

	@Autowired
	private ITOLFloorRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TOLFloor newInstance() {
		TOLFloor po = new TOLFloor();
		JsonContainer<String> result = codeClient.next(OnlineleasingSerialCode.OLFLOOR.getSerialGroup());
		checkJsonContainer(result);
		po.setCode(result.getData());
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLFloor findOneByHdUuid(String hdUuid) {
		return repository.findOneByHdUuid(hdUuid);
	}


	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLFloor> findAllByBuildingCodeInAndDescriptionAndHdState(Collection<String> buildingCodes, String description, String hdState) {
		return repository.findAllByBuildingCodeInAndDescriptionAndHdState(buildingCodes, description, hdState);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<String> findAllDescriptionByBuildingCodeIn(Collection<String> buildingCodes) {
		return repository.findAllDescriptionByBuildingCodeIn(buildingCodes);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLFloor> findAllByHdState(String hdState) {
		return repository.findAllByHdState(hdState);
	}
}
