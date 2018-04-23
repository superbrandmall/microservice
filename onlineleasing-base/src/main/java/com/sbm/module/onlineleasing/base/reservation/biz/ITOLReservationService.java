package com.sbm.module.onlineleasing.base.reservation.biz;

import com.sbm.module.onlineleasing.base.reservation.domain.TOLReservation;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITOLReservationService extends IOLDataService<TOLReservation, Integer> {

	TOLReservation newInstance();

	Page<TOLReservation> findAllByUserCode(String userCode, Pageable pageable);

}
