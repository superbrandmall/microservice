package com.sbm.module.onlineleasing.base.bid.biz.impl;

import com.sbm.module.common.authorization.api.serialcode.client.ISerialCodeClient;
import com.sbm.module.common.authorization.api.serialcode.constant.SerialCodeConstant;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.base.bid.biz.ITOLBidService;
import com.sbm.module.onlineleasing.base.bid.domain.TOLBid;
import com.sbm.module.onlineleasing.base.bid.repository.ITOLBidRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TOLBidServiceImpl extends OLDataServiceImpl<TOLBid, Integer> implements ITOLBidService {

	@Autowired
	private ITOLBidRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;

	@Override
	public TOLBid newInstance() {
		TOLBid po = new TOLBid();
		JsonContainer<String> result = codeClient.next(SerialCodeConstant.OLBID);
		checkJsonContainer(result);
		po.setCode(result.getData());
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLBid findOneByBillNumber(String billNumber) {
		return repository.findOneByBillNumber(billNumber);
	}
}
