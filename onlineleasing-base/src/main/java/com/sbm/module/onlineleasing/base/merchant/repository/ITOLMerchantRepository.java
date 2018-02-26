package com.sbm.module.onlineleasing.base.merchant.repository;

import com.sbm.module.onlineleasing.base.merchant.domain.TOLMerchant;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "merchant")
public interface ITOLMerchantRepository extends IOLDataRepository<TOLMerchant, Integer> {

	TOLMerchant findOneByHdUuid(String hdUuid);

}
