package com.sbm.module.onlineleasing.base.merchantbrand.repository;

import com.sbm.module.common.data.repository.IDataRepository;
import com.sbm.module.onlineleasing.base.merchantbrand.domain.TOLMerchantBrand;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "merchantbrand")
public interface ITOLMerchantBrandRepository extends IDataRepository<TOLMerchantBrand, Integer> {

	List<TOLMerchantBrand> findAllByMerchantCode(String merchantCode);

	List<TOLMerchantBrand> findAllByBrandCode(String brandCode);

}
