package com.sbm.module.onlineleasing.base.reservation.biz.impl;

import com.sbm.module.common.authorization.api.serialcode.client.ISerialCodeClient;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.base.reservation.biz.ITOLReservationService;
import com.sbm.module.onlineleasing.base.reservation.domain.TOLReservation;
import com.sbm.module.onlineleasing.base.reservation.repository.ITOLReservationRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import com.sbm.module.onlineleasing.serialcode.OnlineleasingSerialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TOLReservationServiceImpl extends OLDataServiceImpl<TOLReservation, Integer> implements ITOLReservationService {

	@Autowired
	private ITOLReservationRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TOLReservation newInstance() {
		TOLReservation po = new TOLReservation();
		JsonContainer<String> result = codeClient.next(OnlineleasingSerialCode.OLRESERVATION.getSerialGroup());
		checkJsonContainer(result);
		po.setCode(result.getData());
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TOLReservation> findAllByUserCode(String userCode, Pageable pageable) {
		return repository.findAllByUserCode(userCode, pageable);
	}
}
