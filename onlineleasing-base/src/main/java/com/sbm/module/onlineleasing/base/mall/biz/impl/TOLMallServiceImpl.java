package com.sbm.module.onlineleasing.base.mall.biz.impl;

import com.sbm.module.common.authorization.api.serialcode.client.ISerialCodeClient;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.onlineleasing.base.mall.repository.ITOLMallRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import com.sbm.module.onlineleasing.serialcode.OnlineleasingSerialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TOLMallServiceImpl extends OLDataServiceImpl<TOLMall, Integer> implements ITOLMallService {

	@Autowired
	private ITOLMallRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TOLMall newInstance() {
		TOLMall po = new TOLMall();
		JsonContainer<String> result = codeClient.next(OnlineleasingSerialCode.OLMALL.getSerialGroup());
		checkJsonContainer(result);
		po.setCode(result.getData());
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLMall findOneByHdUuid(String hdUuid) {
		return repository.findOneByHdUuid(hdUuid);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLMall findOneByHdCodeAndHdState(String hdCode, String hdState) {
		return repository.findOneByHdCodeAndHdState(hdCode, hdState);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLMall> findAllByHdState(String hdState) {
		return repository.findAllByHdState(hdState);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLMall> findAllByHdStateOrderByPosition(String hdState) {
		return repository.findAllByHdStateOrderByPosition(hdState);
	}
}
