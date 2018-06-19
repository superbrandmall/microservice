package com.sbm.module.common.message.base.mailsenddetail.biz.impl;

import com.sbm.module.common.authorization.api.serialcode.client.ISerialCodeClient;
import com.sbm.module.common.authorization.serialcode.AuthorizationSerialCode;
import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.message.base.mailsenddetail.biz.ITCMailSendDetailService;
import com.sbm.module.common.message.base.mailsenddetail.domain.TCMailSendDetail;
import com.sbm.module.common.message.base.mailsenddetail.repository.ITCMailSendDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TCMailSendDetailServiceImpl extends DataServiceImpl<TCMailSendDetail, Integer> implements ITCMailSendDetailService {

	@Autowired
	private ITCMailSendDetailRepository repository;

	@Autowired
	private ISerialCodeClient codeClient;


	@Override
	public TCMailSendDetail newInstance() {
		TCMailSendDetail po = new TCMailSendDetail();
		JsonContainer<String> result = codeClient.next(AuthorizationSerialCode.CMAILDETAIL.getSerialGroup());
		checkJsonContainer(result);
		po.setCode(result.getData());
		return po;
	}
}
