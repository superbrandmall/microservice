package com.sbm.module.onlineleasing.base.merchantbankaccount.repository;

import com.sbm.module.onlineleasing.base.merchantbankaccount.domain.TOLMerchantBankAccount;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "merchantbankaccount")
public interface ITOLMerchantBankAccountRepository extends IOLDataRepository<TOLMerchantBankAccount, Integer> {

}
