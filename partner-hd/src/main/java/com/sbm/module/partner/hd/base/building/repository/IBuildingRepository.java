package com.sbm.module.partner.hd.base.building.repository;

import com.sbm.module.common.data.dao.IDataRepository;
import com.sbm.module.partner.hd.base.building.domain.Building;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "building")
public interface IBuildingRepository extends IDataRepository<Building, String> {


}
