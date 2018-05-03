package com.sbm.module.onlineleasing.base.reservationdetail.repository;

import com.sbm.module.common.data.repository.IDataRepository;
import com.sbm.module.onlineleasing.base.reservationdetail.domain.TOLReservationShop;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "reservationshop")
public interface ITOLReservationShopRepository extends IDataRepository<TOLReservationShop, Integer> {

	List<TOLReservationShop> findAllByReservationCode(String reservationCode);

}
