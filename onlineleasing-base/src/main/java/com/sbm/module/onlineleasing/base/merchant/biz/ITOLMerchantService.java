package com.sbm.module.onlineleasing.base.merchant.biz;

import com.sbm.module.onlineleasing.base.merchant.domain.TOLMerchant;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

public interface ITOLMerchantService extends IOLDataService<TOLMerchant, Integer> {

	TOLMerchant newInstance();

	TOLMerchant findOneByHdUuid(String hdUuid);

	TOLMerchant findOneByUscc(String uscc);

}
