package com.sbm.module.onlineleasing.base.reservationdetail.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.onlineleasing.base.reservationdetail.biz.ITOLReservationShopService;
import com.sbm.module.onlineleasing.base.reservationdetail.domain.TOLReservationShop;
import com.sbm.module.onlineleasing.base.reservationdetail.repository.ITOLReservationShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TOLReservationShopServiceImpl extends DataServiceImpl<TOLReservationShop, Integer> implements ITOLReservationShopService {

	@Autowired
	private ITOLReservationShopRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLReservationShop> findAllByReservationCode(String reservationCode) {
		return repository.findAllByReservationCode(reservationCode);
	}
}
