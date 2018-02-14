package com.sbm.module.onlineleasing.base.floor.biz.imp;

import com.sbm.module.onlineleasing.base.building.domain.TOLBuilding;
import com.sbm.module.onlineleasing.base.floor.biz.ITOLFloorService;
import com.sbm.module.onlineleasing.base.floor.domain.TOLFloor;
import com.sbm.module.onlineleasing.base.floor.repository.ITOLFloorRepository;
import com.sbm.module.onlineleasing.base.serialcode.constant.SerialCodeConstant;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TOLFloorServiceImpl extends OLDataServiceImpl<TOLFloor, Integer> implements ITOLFloorService {

	@Autowired
	private ITOLFloorRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLFloor findOneByHdUuid(String hdUuid) {
		return repository.findOneByHdUuid(hdUuid);
	}

	@Override
	@Transactional
	public <S extends TOLFloor> S save(S po) {
		if (StringUtils.isEmpty(po.getCode())) {
			po.setCode(serialCodeService.next(SerialCodeConstant.OLFLOOR).getNext());
		}
		return super.save(po);
	}
}
