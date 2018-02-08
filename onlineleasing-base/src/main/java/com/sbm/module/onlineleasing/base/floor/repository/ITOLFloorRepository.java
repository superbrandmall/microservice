package com.sbm.module.onlineleasing.base.floor.repository;

import com.sbm.module.onlineleasing.base.floor.domain.TOLFloor;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "floor")
public interface ITOLFloorRepository extends IOLDataRepository<TOLFloor, Integer> {

	TOLFloor findOneByHdUuid(String hdUuid);

}
