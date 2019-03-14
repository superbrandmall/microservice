package com.sbm.module.sync.bi.api.sales.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.sync.bi.api.sales.biz.ISalesService;
import com.sbm.module.sync.bi.api.sales.domain.Sales;
import com.sbm.module.sync.bi.api.sales.domain.SalesQuery;
import com.sbm.module.sync.bi.api.sales.domain.SalesSum;
import com.sbm.module.sync.bi.base.olsales.biz.IOlSalesService;
import com.sbm.module.sync.bi.base.olsales.domain.OlSales;
import com.sbm.module.sync.bi.base.olsales.domain.OlSalesPK;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class SalesServiceImpl extends CommonServiceImpl implements ISalesService {

	@Autowired
	private IRedisService redisService;
	@Autowired
	private IOlSalesService olSalesService;

	@Override
	public Sales findOne(Sales sales) {
		return mapOneIfNotNull(olSalesService.findOne(new OlSalesPK(sales.getBuildunit(), sales.getYyyymmdd(), sales.getBrandName())), e -> convert(e));
	}

	private Sales convert(OlSales e) {
		return new Sales(e.getContractNo(), e.getPk().getBuildunit(), e.getPk().getYyyymmdd(), e.getPk().getBrandName(), e.getTotal(), e.getBegindate(), e.getRealenddate());
	}

	@Override
	@SneakyThrows
	public List<Sales> findAllByBuildunitAndBrandNameAndyyyymmddBetween(SalesQuery query) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar ca = Calendar.getInstance();
		ca.setTime(sdf.parse(query.getYyyymmdd()));
		ca.add(Calendar.WEEK_OF_YEAR, -52);

		String startdate = sdf.format(ca.getTime());
		String enddate = query.getYyyymmdd();
		return map(olSalesService.findAllByBuildunitAndBrandNameAndyyyymmddBetween(query.getBuildunit(), query.getBrandName(), startdate, enddate),
				e -> convert(e));
	}

	@Override
	public SalesSum calSales(SalesQuery query) {
		return null;
	}
}
