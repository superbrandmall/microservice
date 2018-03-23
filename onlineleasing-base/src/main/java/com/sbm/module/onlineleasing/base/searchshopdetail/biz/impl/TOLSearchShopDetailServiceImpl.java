package com.sbm.module.onlineleasing.base.searchshopdetail.biz.impl;

import com.sbm.module.common.authorization.api.serialcode.client.ISerialCodeClient;
import com.sbm.module.common.authorization.api.serialcode.constant.SerialCodeConstant;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.base.searchshopdetail.biz.ITOLSearchShopDetailService;
import com.sbm.module.onlineleasing.base.searchshopdetail.domain.TOLSearchShopDetail;
import com.sbm.module.onlineleasing.base.searchshopdetail.repository.ITOLSearchShopDetailRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOLSearchShopDetailServiceImpl extends OLDataServiceImpl<TOLSearchShopDetail, Integer> implements ITOLSearchShopDetailService {

	@Autowired
	private ITOLSearchShopDetailRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TOLSearchShopDetail newInstance() {
		TOLSearchShopDetail po = new TOLSearchShopDetail();
		JsonContainer<String> result = codeClient.next(SerialCodeConstant.OLSEARCHSHOP);
		checkJsonContainer(result);
		po.setCode(result.getData());
		return po;
	}

}
