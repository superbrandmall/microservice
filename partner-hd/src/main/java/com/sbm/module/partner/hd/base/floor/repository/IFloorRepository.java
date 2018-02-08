package com.sbm.module.partner.hd.base.floor.repository;

import com.sbm.module.common.data.repository.IDataRepository;
import com.sbm.module.partner.hd.base.floor.domain.Floor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "floor")
public interface IFloorRepository extends IDataRepository<Floor, String> {


}
