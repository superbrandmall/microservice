package com.sbm.module.onlineleasing.customer.reservation.biz;

import com.sbm.module.onlineleasing.domain.reservation.ReservationResult;

public interface IReservationMessageService {

	void send(ReservationResult vo);

}
