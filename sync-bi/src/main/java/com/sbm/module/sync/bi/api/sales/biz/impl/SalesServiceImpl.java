package com.sbm.module.sync.bi.api.sales.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.sync.bi.api.sales.biz.ISalesService;
import com.sbm.module.sync.bi.api.sales.domain.Sales;
import com.sbm.module.sync.bi.api.sales.domain.SalesQuery;
import com.sbm.module.sync.bi.api.sales.domain.SalesResult;
import com.sbm.module.sync.bi.api.sales.domain.SalesSum;
import com.sbm.module.sync.bi.base.olsales.biz.IOlSalesService;
import com.sbm.module.sync.bi.base.olsales.domain.OlSales;
import com.sbm.module.sync.bi.base.olsales.domain.OlSalesPK;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class SalesServiceImpl extends CommonServiceImpl implements ISalesService {

	@Autowired
	private IRedisService redisService;
	@Autowired
	private IOlSalesService olSalesService;

	public static final int WEEKS_52 = 52 * 7;
	public static final int WEEKS_26 = 26 * 7;
	public static final int WEEKS_13 = 13 * 7;
	public static final int WEEKS_4 = 4 * 7;


	@Override
	public Sales findOne(Sales sales) {
		return mapOneIfNotNull(olSalesService.findOne(new OlSalesPK(sales.getBuildunit(), sales.getYyyymmdd(), sales.getBrandName())), e -> convert(e));
	}

	private Sales convert(OlSales e) {
		return new Sales(e.getContractNo(), e.getPk().getBuildunit(), e.getPk().getYyyymmdd(), e.getPk().getBrandName(), e.getTotal(), e.getBegindate(), e.getRealenddate());
	}

	@Override
	@SneakyThrows
	public SalesResult findAllByBuildunitAndBrandNameAndyyyymmddBetween(SalesQuery query) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar ca = Calendar.getInstance();
		ca.setTime(sdf.parse(query.getYyyymmdd()));
		ca.add(Calendar.WEEK_OF_YEAR, -52);

		String startdate = sdf.format(ca.getTime());
		String enddate = query.getYyyymmdd();
		return new SalesResult(map(olSalesService.findAllByPk_BuildunitAndPk_BrandNameAndPk_YyyymmddBetweenOrderByPk_YyyymmddDesc(query.getBuildunit(), query.getBrandName(), startdate, enddate),
				e -> convert(e)), startdate, enddate);
	}

	@Override
	public SalesSum calSales(SalesQuery query) {
		SalesResult salesResult = findAllByBuildunitAndBrandNameAndyyyymmddBetween(query);
		List<Sales> sales = salesResult.getSales();
		Integer size = sales.size();
		int tmp = 0;
		if (size >= WEEKS_52) {
			tmp = WEEKS_52;
		} else if (WEEKS_26 <= size && size < WEEKS_52) {
			tmp = WEEKS_26;
		} else if (WEEKS_13 <= size && size < WEEKS_26) {
			tmp = WEEKS_13;
		} else if (WEEKS_4 <= size && size < WEEKS_13) {
			tmp = WEEKS_4;
		} else if (0 <= size && size < WEEKS_4) {
			tmp = 0;
		}
		BigDecimal result = sales.stream().limit(tmp).map(Sales::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		return new SalesSum(tmp / 7, result, salesResult.getStartdate(), salesResult.getEnddate());
	}
}
