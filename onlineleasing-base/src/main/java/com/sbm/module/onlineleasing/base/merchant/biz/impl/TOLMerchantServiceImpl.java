package com.sbm.module.onlineleasing.base.merchant.biz.impl;

import com.sbm.module.common.authorization.api.serialcode.client.ISerialCodeClient;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.base.merchant.biz.ITOLMerchantService;
import com.sbm.module.onlineleasing.base.merchant.domain.TOLMerchant;
import com.sbm.module.onlineleasing.base.merchant.repository.ITOLMerchantRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import com.sbm.module.onlineleasing.serialcode.OnlineleasingSerialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TOLMerchantServiceImpl extends OLDataServiceImpl<TOLMerchant, Integer> implements ITOLMerchantService {

	@Autowired
	private ITOLMerchantRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TOLMerchant newInstance() {
		TOLMerchant po = new TOLMerchant();
		JsonContainer<String> result = codeClient.next(OnlineleasingSerialCode.OLMERCHANT.getSerialGroup());
		checkJsonContainer(result);
		po.setCode(result.getData());
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLMerchant findOneByHdUuid(String hdUuid) {
		return repository.findOneByHdUuid(hdUuid);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLMerchant findOneByUscc(String uscc) {
		return repository.findOneByUscc(uscc);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLMerchant> findAllByTianyanchaIdIsNull() {
		return repository.findAllByTianyanchaIdIsNull();
	}
}
