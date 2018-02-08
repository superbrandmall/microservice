package com.sbm.module.onlineleasing.base.building.repository;

import com.sbm.module.onlineleasing.base.building.domain.TOLBuilding;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "building")
public interface ITOLBuildingRepository extends IOLDataRepository<TOLBuilding, Integer> {

	TOLBuilding findOneByHdUuid(String hdUuid);

}
