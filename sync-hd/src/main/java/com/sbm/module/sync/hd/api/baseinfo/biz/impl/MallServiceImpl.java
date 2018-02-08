package com.sbm.module.sync.hd.api.baseinfo.biz.impl;

import com.sbm.module.common.biz.impl.BusinessServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.partner.hd.api.mall.client.IHdMallClient;
import com.sbm.module.partner.hd.api.mall.domain.HdMall;
import com.sbm.module.sync.hd.api.baseinfo.biz.IMallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallServiceImpl extends BusinessServiceImpl<TOLMall, HdMall> implements IMallService {

	@Autowired
	private IHdMallClient hdMallClient;
	@Autowired
	private ITOLMallService mallService;

	@Override
	public void refresh() {
		JsonContainer<List<HdMall>> result = hdMallClient.findAllVo();
		List<TOLMall> pos = findAll(result.getData());
		mallService.save(pos);
	}

	@Override
	public TOLMall newInstance(HdMall e) {

		return null;
	}
}
