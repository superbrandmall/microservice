package com.sbm.module.onlineleasing.base.brandshopsample.repository;

import com.sbm.module.onlineleasing.base.brandshopsample.domain.TOLBrandShopSample;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "brandshopsample")
public interface ITOLBrandShopSampleRepository extends IOLDataRepository<TOLBrandShopSample, Integer> {


}
