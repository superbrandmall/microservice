package com.sbm.module.onlineleasing.customer.reservation.biz;

import com.sbm.module.onlineleasing.domain.reservation.Reservation;

public interface IReservationMessageService {

	void send(Reservation vo);

}
