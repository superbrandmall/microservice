package com.sbm.module.onlineleasing.base.shopcoords.repository;

import com.sbm.module.onlineleasing.base.shopcoords.domain.TOLShopCoords;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "shopcoords")
public interface ITOLShopCoordsRepository extends IOLDataRepository<TOLShopCoords, Integer> {


}
