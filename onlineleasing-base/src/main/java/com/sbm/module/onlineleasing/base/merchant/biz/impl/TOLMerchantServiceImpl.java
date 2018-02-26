package com.sbm.module.onlineleasing.base.merchant.biz.impl;

import com.sbm.module.onlineleasing.base.merchant.biz.ITOLMerchantService;
import com.sbm.module.onlineleasing.base.merchant.domain.TOLMerchant;
import com.sbm.module.onlineleasing.base.merchant.repository.ITOLMerchantRepository;
import com.sbm.module.onlineleasing.base.serialcode.constant.SerialCodeConstant;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TOLMerchantServiceImpl extends OLDataServiceImpl<TOLMerchant, Integer> implements ITOLMerchantService {

	@Autowired
	private ITOLMerchantRepository repository;

	@Override
	public TOLMerchant newInstance() {
		TOLMerchant po = new TOLMerchant();
		po.setCode(serialCodeService.next(SerialCodeConstant.OLMERCHANT).getNext());
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLMerchant findOneByHdUuid(String hdUuid) {
		return repository.findOneByHdUuid(hdUuid);
	}

}
