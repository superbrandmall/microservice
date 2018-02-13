package com.sbm.module.onlineleasing.base.shopengineeringspecifications.repository;

import com.sbm.module.onlineleasing.base.shopengineeringspecifications.domain.TOLShopEngineeringSpecifications;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "shopengineeringspecifications")
public interface ITOLShopEngineeringSpecificationsRepository extends IOLDataRepository<TOLShopEngineeringSpecifications, Integer> {

}
