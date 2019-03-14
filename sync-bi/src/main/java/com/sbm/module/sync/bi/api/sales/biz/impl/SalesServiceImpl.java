package com.sbm.module.sync.bi.api.sales.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.sync.bi.api.sales.biz.ISalesService;
import com.sbm.module.sync.bi.api.sales.domain.Sales;
import com.sbm.module.sync.bi.base.olsales.biz.IOlSalesService;
import com.sbm.module.sync.bi.base.olsales.domain.OlSales;
import com.sbm.module.sync.bi.base.olsales.domain.OlSalesPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesServiceImpl extends CommonServiceImpl implements ISalesService {

	@Autowired
	private IRedisService redisService;
	@Autowired
	private IOlSalesService olSalesService;

	@Override
	public Sales findOne(Sales sales) {
		return mapOneIfNotNull(olSalesService.findOne(new OlSalesPK(sales.getContractNo(), sales.getBuildunit(), sales.getYyyymmdd(), sales.getBrandName())), e -> convert(e));
	}

	private Sales convert(OlSales e) {
		return new Sales(e.getPk().getContractNo(), e.getPk().getBuildunit(), e.getPk().getYyyymmdd(), e.getPk().getBrandName(), e.getTotal(), e.getBegindate(), e.getRealenddate());
	}

}
