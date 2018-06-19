package com.sbm.module.onlineleasing.base.brand.biz.impl;

import com.sbm.module.common.authorization.api.serialcode.client.ISerialCodeClient;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.base.brand.biz.ITOLBrandService;
import com.sbm.module.onlineleasing.base.brand.domain.TOLBrand;
import com.sbm.module.onlineleasing.base.brand.repository.ITOLBrandRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import com.sbm.module.onlineleasing.serialcode.OnlineleasingSerialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TOLBrandServiceImpl extends OLDataServiceImpl<TOLBrand, Integer> implements ITOLBrandService {

	@Autowired
	private ITOLBrandRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TOLBrand newInstance() {
		TOLBrand po = new TOLBrand();
		JsonContainer<String> result = codeClient.next(OnlineleasingSerialCode.OLBRAND.getSerialGroup());
		checkJsonContainer(result);
		po.setCode(result.getData());
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLBrand findOneByHdUuid(String hdUuid) {
		return repository.findOneByHdUuid(hdUuid);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLBrand findOneByName(String name) {
		return repository.findOneByName(name);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLBrand> findAllByNameContaining(String name) {
		return repository.findAllByNameContaining(name);
	}
}
