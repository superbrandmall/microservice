package com.sbm.module.onlineleasing.base.merchantaddress.repository;

import com.sbm.module.onlineleasing.base.merchantaddress.domain.TOLMerchantAddress;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "merchantaddress")
public interface ITOLMerchantAddressRepository extends IOLDataRepository<TOLMerchantAddress, Integer> {

}
