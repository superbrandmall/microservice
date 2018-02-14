package com.sbm.module.sync.hd.api.shop.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.biz.impl.SyncServiceImpl;
import com.sbm.module.common.domain.SyncResult;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.partner.hd.rest.base.domain.HdQueryFilter;
import com.sbm.module.partner.hd.rest.shop.client.IHdShopClient;
import com.sbm.module.partner.hd.rest.shop.domain.HdShop;
import com.sbm.module.sync.hd.api.shop.biz.IShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ShopServiceImpl extends SyncServiceImpl<TOLShop, HdShop, HdQueryFilter> implements IShopService {

	@Autowired
	private IHdShopClient hdShopClient;
	@Autowired
	private ITOLShopService shopService;

	@Override
	@Scheduled(cron = "${sync.cron.brand}")
	public void refresh() {
		HdQueryFilter filter = new HdQueryFilter();
		filter.getFilter().put("type", "shoppe");
		execute(filter);
	}

	@Override
	public TOLShop newInstance(HdShop e) {
		System.out.println(JSON.toJSONString(e));
		TOLShop po = shopService.findOneByHdUuid(e.getUuid());
		System.out.println(JSON.toJSONString(po));
		return po;
	}


	@Override
	protected void save(List<TOLShop> pos) {
		System.out.println("i am in save");
	}

	@Override
	protected SyncResult<HdShop> getResult(HdQueryFilter filter) {
		return hdShopClient.query(filter).getBody().toSyncResult();
	}

	@Override
	protected void doAfter(HdQueryFilter filter) {
		filter.setPage(filter.getPage() + 1);
	}

	@Override
	protected boolean whileCondition(HdQueryFilter filter, SyncResult<HdShop> result) {
		return filter.getPage() < result.getPageCount();
	}
}
