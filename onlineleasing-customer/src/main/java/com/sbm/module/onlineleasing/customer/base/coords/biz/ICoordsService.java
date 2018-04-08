package com.sbm.module.onlineleasing.customer.base.coords.biz;

import com.sbm.module.onlineleasing.domain.base.coords.Coords;

import java.util.List;

public interface ICoordsService {

	List<Coords> findAllByMallCodeAndDescription(String mallCode, String description);

}
