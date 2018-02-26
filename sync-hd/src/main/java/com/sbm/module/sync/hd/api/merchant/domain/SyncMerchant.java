package com.sbm.module.sync.hd.api.merchant.domain;

import com.sbm.module.onlineleasing.base.merchant.domain.TOLMerchant;
import com.sbm.module.onlineleasing.base.merchantaddress.domain.TOLMerchantAddress;
import com.sbm.module.onlineleasing.base.merchantbankaccount.domain.TOLMerchantBankAccount;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SyncMerchant {

	private TOLMerchant merchant;

	private TOLMerchantAddress merchantAddress;

	private List<TOLMerchantBankAccount> merchantBankAccounts = new ArrayList<>();

}
