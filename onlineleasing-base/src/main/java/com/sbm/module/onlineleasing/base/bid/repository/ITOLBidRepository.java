package com.sbm.module.onlineleasing.base.bid.repository;

import com.sbm.module.onlineleasing.base.bid.domain.TOLBid;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "bid")
public interface ITOLBidRepository extends IOLDataRepository<TOLBid, Integer> {

	TOLBid findOneByBillNumber(String billNumber);

}
