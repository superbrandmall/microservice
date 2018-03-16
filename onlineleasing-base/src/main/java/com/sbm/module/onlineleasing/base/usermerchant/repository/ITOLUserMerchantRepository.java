package com.sbm.module.onlineleasing.base.usermerchant.repository;

import com.sbm.module.common.data.repository.IDataRepository;
import com.sbm.module.onlineleasing.base.usermerchant.domain.TOLUserMerchant;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "usermerchant")
public interface ITOLUserMerchantRepository extends IDataRepository<TOLUserMerchant, Integer> {

	List<TOLUserMerchant> findAllByUserCode(String userCode);

	List<TOLUserMerchant> findAllByMerchantCode(String merchantCode);

	TOLUserMerchant findOneByUserCodeAndMerchantCode(String userCode, String merchantCode);

}
