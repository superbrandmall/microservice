package com.sbm.module.sync.hd.api.shop.biz.impl;

import com.sbm.module.common.biz.impl.SyncServiceImpl;
import com.sbm.module.common.domain.SyncResult;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.partner.hd.rest.base.domain.HdQueryFilter;
import com.sbm.module.partner.hd.rest.shop.client.IHdShopClient;
import com.sbm.module.partner.hd.rest.shop.domain.HdShop;
import com.sbm.module.sync.hd.api.shop.biz.IShopService;
import com.sbm.module.sync.hd.api.shop.domain.SyncShop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShopServiceImpl extends SyncServiceImpl<SyncShop, HdShop, HdQueryFilter> implements IShopService {

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
	public SyncShop newInstance(HdShop e) {
		SyncShop sync = new SyncShop();
		TOLShop shop = shopService.findOneByHdUuid(e.getUuid());
		if (null == shop) {
			shop = new TOLShop();
		}
		sync.setShop(shop);

		return sync;
	}

	@Override
	protected void save(List<SyncShop> pos) {
		System.out.println("i am in save");
		//shopService.save(pos.stream().map(e -> e.getShop()).collect(Collectors.toList()));
	}

	@Override
	protected SyncResult<HdShop> getResult(HdQueryFilter filter) {
		return hdShopClient.query(filter).getBody().toSyncResult();
	}

	@Override
	protected void doAfter(HdQueryFilter filter) {
		filter.addOne();
	}

	@Override
	protected boolean whileCondition(HdQueryFilter filter, SyncResult<HdShop> result) {
		return filter.getPage() < result.getPageCount();
	}
}
