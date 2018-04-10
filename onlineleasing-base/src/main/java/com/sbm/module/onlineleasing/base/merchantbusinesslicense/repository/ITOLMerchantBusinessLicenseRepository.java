package com.sbm.module.onlineleasing.base.merchantbusinesslicense.repository;

import com.sbm.module.onlineleasing.base.merchantbusinesslicense.domain.TOLMerchantBusinessLicense;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "merchantbusinesslicense")
public interface ITOLMerchantBusinessLicenseRepository extends IOLDataRepository<TOLMerchantBusinessLicense, Integer> {

}
