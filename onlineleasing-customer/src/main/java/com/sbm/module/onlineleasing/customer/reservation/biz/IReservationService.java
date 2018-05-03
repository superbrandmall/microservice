package com.sbm.module.onlineleasing.customer.reservation.biz;

import com.sbm.module.onlineleasing.domain.reservation.Reservation;
import com.sbm.module.onlineleasing.domain.reservation.ReservationShopInfo;
import com.sbm.module.onlineleasing.domain.reservation.ReservationUserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IReservationService {

	ReservationUserInfo getReservationUserInfo(String userCode);

	ReservationShopInfo getReservationShopInfo(String shopCode);

	void save(Reservation<String> vo);

	Page<Reservation<ReservationShopInfo>> getDetails(String userCode, Pageable pageable);

}
