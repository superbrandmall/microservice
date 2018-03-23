package com.sbm.module.onlineleasing.base.shop.biz.impl;

import com.sbm.module.common.authorization.api.serialcode.client.ISerialCodeClient;
import com.sbm.module.common.authorization.api.serialcode.constant.SerialCodeConstant;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.base.shop.repository.ITOLShopRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class TOLShopServiceImpl extends OLDataServiceImpl<TOLShop, Integer> implements ITOLShopService {

	@Autowired
	private ITOLShopRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TOLShop newInstance() {
		TOLShop po = new TOLShop();
		JsonContainer<String> result = codeClient.next(SerialCodeConstant.OLSHOP);
		checkJsonContainer(result);
		po.setCode(result.getData());
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLShop findOneByHdUuid(String hdUuid) {
		return repository.findOneByHdUuid(hdUuid);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLShop> findAllByFloorCodeInAndShopStateAndHdState(Collection<String> floorCodes, Integer shopState, String hdState) {
		return repository.findAllByFloorCodeInAndShopStateAndHdState(floorCodes, shopState, hdState);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLShop> findAllByMallCodeAndShopStateAndHdState(String mallCode, Integer shopState, String hdState) {
		return repository.findAllByMallCodeAndShopStateAndHdState(mallCode, shopState, hdState);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLShop> findAllBySearchShop(Collection<String> mallCodes) {
		return repository.findAllBySearchShop(mallCodes);
	}
}
