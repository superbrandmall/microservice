package com.sbm.module.onlineleasing.base.usermerchant.biz;

import com.sbm.module.common.data.biz.IDataService;
import com.sbm.module.onlineleasing.base.usermerchant.domain.TOLUserMerchant;

import java.util.List;

public interface ITOLUserMerchantService extends IDataService<TOLUserMerchant, Integer> {

	List<TOLUserMerchant> findAllByUserCode(String userCode);

	List<TOLUserMerchant> findAllByMerchantCode(String merchantCode);

	TOLUserMerchant findOneByUserCodeAndMerchantCode(String userCode, String merchantCode);

}
