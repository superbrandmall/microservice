package com.sbm.module.onlineleasing.customer.reservation.biz;

import com.sbm.module.onlineleasing.domain.reservation.Reservation;
import com.sbm.module.onlineleasing.domain.reservation.ReservationResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IReservationService {

	Page<ReservationResult> getDetails(String userCode, Pageable pageable);

	Reservation findOneByShopCodeAndUserCode(String shopCode, String userCode);

	void save(ReservationResult vo);


}
