package com.sbm.module.common.message.base.smssenddetail.biz.impl;

import com.sbm.module.common.authorization.api.serialcode.client.ISerialCodeClient;
import com.sbm.module.common.authorization.api.serialcode.constant.SerialCodeConstant;
import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.message.base.smssenddetail.biz.ITCSMSSendDetailService;
import com.sbm.module.common.message.base.smssenddetail.domain.TCSMSSendDetail;
import com.sbm.module.common.message.base.smssenddetail.repository.ITCSMSSendDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TCSMSSendDetailServiceImpl extends DataServiceImpl<TCSMSSendDetail, Integer> implements ITCSMSSendDetailService {

	@Autowired
	private ITCSMSSendDetailRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;


	@Override
	public TCSMSSendDetail newInstance() {
		TCSMSSendDetail po = new TCSMSSendDetail();
		JsonContainer<String> result = codeClient.next(SerialCodeConstant.CSMSDETAIL);
		checkJsonContainer(result);
		po.setCode(result.getData());
		return po;
	}
}
