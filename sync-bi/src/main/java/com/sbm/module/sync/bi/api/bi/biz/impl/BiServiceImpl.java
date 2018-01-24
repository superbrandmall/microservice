package com.sbm.module.sync.bi.api.bi.biz.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.sync.bi.api.bi.biz.IBiService;
import com.sbm.module.sync.bi.api.bi.domain.Bi;
import com.sbm.module.sync.bi.api.bi.domain.BiDetail;
import com.sbm.module.sync.bi.base.salesreport.biz.ISalesreportSummarydataService;
import com.sbm.module.sync.bi.base.salesreport.domain.SalesreportSummarydata;
import com.sbm.module.sync.bi.base.summarypassenger.biz.ISummaryPassengerService;
import com.sbm.module.sync.bi.base.summarypassenger.domain.SummaryPassenger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class BiServiceImpl implements IBiService {

	@Autowired
	private IRedisService redisService;
	@Autowired
	private ISalesreportSummarydataService salesreportSummarydataService;
	@Autowired
	private ISummaryPassengerService summaryPassengerService;

	private static final String PREFIX = "BI_";

	@Override
	public void findByBuildingCode(Bi vo) {
		String valuer = (String) redisService.get(PREFIX + vo.getMallCode());
		if (StringUtils.isNotBlank(valuer)) {
			List<BiDetail> details = JSON.parseArray(valuer, BiDetail.class);
			vo.setDetails(details);
		}
	}

	@Override
	public void refresh() {
		List<BiDetail> details = new ArrayList<>();
		BiDetail detail;
		// 广场
		Iterable<SalesreportSummarydata> salesreportSummarydatas = salesreportSummarydataService.findAllByGroup();
		for (SalesreportSummarydata salesreportSummarydata : salesreportSummarydatas) {
			detail = new BiDetail();
			convert(salesreportSummarydata, detail);
			details.add(detail);
		}
		// 乐城
		Iterable<SummaryPassenger> summaryPassengers = summaryPassengerService.findAllByGroup();
		for (SummaryPassenger summaryPassenger : summaryPassengers) {
			detail = new BiDetail();
			convert(summaryPassenger, detail);
			details.add(detail);
		}

		// 根据buildingCode分组
		Map<String, List<BiDetail>> map =
				details.stream().collect(Collectors.groupingBy(BiDetail::getMallCode));
		// 遍历存入redis
		for (String mallCode : map.keySet()) {
			redisService.set2redis(PREFIX + mallCode, JSON.toJSONString(map.get(mallCode)), 2L, TimeUnit.DAYS);
		}
	}

	private void convert(SalesreportSummarydata salesreportSummarydata, BiDetail detail){
		detail.setYyyymmdd(salesreportSummarydata.getPk().getYyyymmdd());
		detail.setMallCode(salesreportSummarydata.getPk().getBuildingCode().substring(0, 6));
		detail.setSalesTy(salesreportSummarydata.getSalesTy());
		detail.setUpTy(salesreportSummarydata.getUpTy());
		detail.setVehicleInTy(salesreportSummarydata.getVehicleInTy());
	}

	private void convert(SummaryPassenger summaryPassenger, BiDetail detail) {
		detail.setYyyymmdd(summaryPassenger.getPk().getYyyymmdd());
		detail.setMallCode(summaryPassenger.getPk().getMallCode());
		detail.setUpTy(summaryPassenger.getInSum());
	}


}
