package com.sbm.module.onlineleasing.customer.reservation.biz;

import com.sbm.module.onlineleasing.domain.reservation.Reservation;
import com.sbm.module.onlineleasing.domain.reservation.ReservationMinInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IReservationService {

	Page<Reservation> getDetails(String userCode, Pageable pageable);

	ReservationMinInfo findOneByShopCodeAndUserCode(String shopCode, String userCode);

	void save(Reservation vo);


}
