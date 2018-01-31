package com.sbm.module.partner.hd.base.building.repository;

import com.sbm.module.partner.hd.base.building.domain.Building;
import com.sbm.module.partner.hd.base.floor.domain.Floor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "building")
public interface IBuildingRepository extends PagingAndSortingRepository<Building, String> {


}
