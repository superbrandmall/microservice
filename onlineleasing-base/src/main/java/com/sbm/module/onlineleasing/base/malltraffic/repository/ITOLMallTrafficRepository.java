package com.sbm.module.onlineleasing.base.malltraffic.repository;

import com.sbm.module.onlineleasing.base.malltraffic.domain.TOLMallTraffic;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "malltraffic")
public interface ITOLMallTrafficRepository extends IOLDataRepository<TOLMallTraffic, Integer> {


}
