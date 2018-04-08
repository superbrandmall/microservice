package com.sbm.module.onlineleasing.base.bidcontract.repository;

import com.sbm.module.onlineleasing.base.bidcontract.domain.TOLBidContract;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "bidcontract")
public interface ITOLBidContractRepository extends IOLDataRepository<TOLBidContract, Integer> {


}
