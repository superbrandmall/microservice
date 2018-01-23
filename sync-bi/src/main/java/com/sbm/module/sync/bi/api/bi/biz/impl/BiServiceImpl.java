package com.sbm.module.sync.bi.api.bi.biz.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.sync.bi.api.bi.biz.IBiService;
import com.sbm.module.sync.bi.api.bi.domain.Bi;
import com.sbm.module.sync.bi.base.salesreport.biz.ISalesreportSummarydataService;
import com.sbm.module.sync.bi.base.salesreport.domain.SalesreportSummarydata;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class BiServiceImpl implements IBiService {

	@Autowired
	private IRedisService redisService;
	@Autowired
	private ISalesreportSummarydataService service;

	private static final String PREFIX = "BI_";

	@Override
	public void findByBuildingCode(Bi vo) {
		String valuer = (String) redisService.get(PREFIX + vo.getBuildingCode());
		if (StringUtils.isNotBlank(valuer)) {
			List<SalesreportSummarydata> details = JSON.parseArray(valuer, SalesreportSummarydata.class);
			vo.setDetails(details);
		}
	}

	@Override
	public void refresh() {
		Iterable<SalesreportSummarydata> iter = service.findAllByGroup();
		List<SalesreportSummarydata> list = Lists.newArrayList(iter);
		// 根据buildingCode分组
		Map<String, List<SalesreportSummarydata>> map =
				list.stream().collect(Collectors.groupingBy(SalesreportSummarydata::getBuildingCode));
		// 遍历存入redis
		for (String buildingCode : map.keySet()) {
			redisService.set2redis(PREFIX + buildingCode, JSON.toJSONString(map.get(buildingCode)), 2L, TimeUnit.DAYS);
		}
	}
}
