package com.sbm.module.onlineleasing.interactive.bid.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.interactive.bid.biz.IBidService;
import com.sbm.module.onlineleasing.interactive.bid.domain.ApproveResult;
import com.sbm.module.onlineleasing.interactive.bid.domain.BidResult;
import com.sbm.module.onlineleasing.interactive.bid.domain.EffectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidServiceImpl extends CommonServiceImpl implements IBidService {


	@Override
	public void approve(BidResult<ApproveResult> vo) {
		System.out.println(JSON.toJSONString(vo));
	}

	@Override
	public void effect(BidResult<EffectResult> vo) {
		vo.getBidResultReceives().forEach(e -> {




		});
	}
}
