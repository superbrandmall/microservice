package com.sbm.module.onlineleasing.base.merchantbankaccount.biz.impl;

import com.sbm.module.onlineleasing.base.merchantbankaccount.biz.ITOLMerchantBankAccountService;
import com.sbm.module.onlineleasing.base.merchantbankaccount.domain.TOLMerchantBankAccount;
import com.sbm.module.onlineleasing.base.merchantbankaccount.repository.ITOLMerchantBankAccountRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOLMerchantBankAccountServiceImpl extends OLDataServiceImpl<TOLMerchantBankAccount, Integer> implements ITOLMerchantBankAccountService {

	@Autowired
	private ITOLMerchantBankAccountRepository repository;

}
