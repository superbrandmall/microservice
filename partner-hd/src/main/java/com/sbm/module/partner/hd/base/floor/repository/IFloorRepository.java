package com.sbm.module.partner.hd.base.floor.repository;

import com.sbm.module.partner.hd.base.floor.domain.Floor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "floor")
public interface IFloorRepository extends PagingAndSortingRepository<Floor, String> {


}
