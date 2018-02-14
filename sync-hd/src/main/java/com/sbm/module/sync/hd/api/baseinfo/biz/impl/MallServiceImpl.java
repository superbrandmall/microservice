package com.sbm.module.sync.hd.api.baseinfo.biz.impl;

import com.sbm.module.common.biz.impl.SyncServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.domain.SyncResult;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.partner.hd.api.mall.client.IHdMallClient;
import com.sbm.module.partner.hd.api.mall.domain.HdMall;
import com.sbm.module.sync.hd.api.baseinfo.biz.IMallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallServiceImpl extends SyncServiceImpl<TOLMall, HdMall, Object> implements IMallService {

	@Autowired
	private IHdMallClient hdMallClient;
	@Autowired
	private ITOLMallService mallService;

	@Override
	public void refresh() {
		execute(null);
	}

	@Override
	public TOLMall newInstance(HdMall e) {
		TOLMall po = mallService.findOneByHdUuid(e.getHdUuid());
		if (null == po) {
			po = new TOLMall();
		}
		// 项目名称
		po.setMallName(e.getHdName());
		// 建筑面积
		po.setGrossFloorArea(e.getGrossFloorArea());
		// 租赁面积
		po.setLeasingArea(e.getLeasingArea());
		// 备注 暂不同步
		//po.setDescription(obj.getDescription());
		// 海鼎uuid
		po.setHdUuid(e.getHdUuid());
		// 海鼎编号
		po.setHdCode(e.getHdCode());
		// 海鼎状态
		po.setHdState(e.getState());
		return po;
	}

	@Override
	protected void save(List<TOLMall> pos) {
		mallService.save(pos);
	}

	@Override
	protected SyncResult<HdMall> getResult(Object filter) {
		JsonContainer<List<HdMall>> result = hdMallClient.findAll();
		return new SyncResult<>(result.getData());
	}

	@Override
	protected void doAfter(Object filter) {

	}

	@Override
	protected boolean whileCondition(Object filter, SyncResult<HdMall> result) {
		return false;
	}
}
