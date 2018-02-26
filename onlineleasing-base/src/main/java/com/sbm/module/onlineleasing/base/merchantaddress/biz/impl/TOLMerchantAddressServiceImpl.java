package com.sbm.module.onlineleasing.base.merchantaddress.biz.impl;

import com.sbm.module.onlineleasing.base.merchantaddress.biz.ITOLMerchantAddressService;
import com.sbm.module.onlineleasing.base.merchantaddress.domain.TOLMerchantAddress;
import com.sbm.module.onlineleasing.base.merchantaddress.repository.ITOLMerchantAddressRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOLMerchantAddressServiceImpl extends OLDataServiceImpl<TOLMerchantAddress, Integer> implements ITOLMerchantAddressService {

	@Autowired
	private ITOLMerchantAddressRepository repository;

}
