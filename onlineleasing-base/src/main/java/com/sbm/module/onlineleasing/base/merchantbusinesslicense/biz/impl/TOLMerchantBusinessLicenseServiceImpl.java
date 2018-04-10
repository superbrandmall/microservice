package com.sbm.module.onlineleasing.base.merchantbusinesslicense.biz.impl;

import com.sbm.module.onlineleasing.base.merchantbusinesslicense.biz.ITOLMerchantBusinessLicenseService;
import com.sbm.module.onlineleasing.base.merchantbusinesslicense.domain.TOLMerchantBusinessLicense;
import com.sbm.module.onlineleasing.base.merchantbusinesslicense.repository.ITOLMerchantBusinessLicenseRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOLMerchantBusinessLicenseServiceImpl extends OLDataServiceImpl<TOLMerchantBusinessLicense, Integer> implements ITOLMerchantBusinessLicenseService {

	@Autowired
	private ITOLMerchantBusinessLicenseRepository repository;

}
