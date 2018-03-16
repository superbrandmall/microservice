package com.sbm.module.onlineleasing.base.usermerchant.biz.impl;


import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.onlineleasing.base.usermerchant.biz.ITOLUserMerchantService;
import com.sbm.module.onlineleasing.base.usermerchant.domain.TOLUserMerchant;
import com.sbm.module.onlineleasing.base.usermerchant.repository.ITOLUserMerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TOLUserMerchantServiceImpl extends DataServiceImpl<TOLUserMerchant, Integer> implements ITOLUserMerchantService {

	@Autowired
	private ITOLUserMerchantRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLUserMerchant> findAllByUserCode(String userCode) {
		return repository.findAllByUserCode(userCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLUserMerchant> findAllByMerchantCode(String merchantCode) {
		return repository.findAllByMerchantCode(merchantCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLUserMerchant findOneByUserCodeAndMerchantCode(String userCode, String merchantCode) {
		return repository.findOneByUserCodeAndMerchantCode(userCode, merchantCode);
	}
}
