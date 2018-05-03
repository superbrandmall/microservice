package com.sbm.module.onlineleasing.base.reservationdetail.biz;

import com.sbm.module.common.data.biz.IDataService;
import com.sbm.module.onlineleasing.base.reservationdetail.domain.TOLReservationShop;

import java.util.List;

public interface ITOLReservationShopService extends IDataService<TOLReservationShop, Integer> {

	List<TOLReservationShop> findAllByReservationCode(String reservationCode);

}
