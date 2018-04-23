package com.sbm.module.onlineleasing.base.reservation.repository;

import com.sbm.module.onlineleasing.base.reservation.domain.TOLReservation;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "reservation")
public interface ITOLReservationRepository extends IOLDataRepository<TOLReservation, Integer> {

	Page<TOLReservation> findAllByUserCode(String userCode, Pageable pageable);

}
