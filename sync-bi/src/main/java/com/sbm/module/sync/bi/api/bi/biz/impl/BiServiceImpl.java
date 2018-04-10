package com.sbm.module.sync.bi.api.bi.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import com.sbm.module.sync.bi.api.bi.biz.IBiService;
import com.sbm.module.sync.bi.api.bi.domain.BiDetail;
import com.sbm.module.sync.bi.base.salesreport.biz.ISalesreportSummarydataService;
import com.sbm.module.sync.bi.base.salesreport.domain.SalesreportSummarydata;
import com.sbm.module.sync.bi.base.summarypassenger.biz.ISummaryPassengerService;
import com.sbm.module.sync.bi.base.summarypassenger.domain.SummaryPassenger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BiServiceImpl extends CommonServiceImpl implements IBiService {

	@Autowired
	private IRedisService redisService;
	@Autowired
	private ISalesreportSummarydataService salesreportSummarydataService;
	@Autowired
	private ISummaryPassengerService summaryPassengerService;

	@Override
	public List<BiDetail> findByMallHdCode(String mallHdCode) {
		List<BiDetail> details = null;
		String valuer = (String) redisService.get(RedisConstant.getKey(BiDetail.class, mallHdCode));
		if (StringUtils.isNotBlank(valuer)) {
			details = JSON.parseArray(valuer, BiDetail.class);
		}
		return details;
	}

	@Override
	@Scheduled(cron = "${sync.cron.bi}")
	public void refresh() {
		List<BiDetail> details = new ArrayList<>();
		// 广场
		List<BiDetail> sbm = map(salesreportSummarydataService.findAllByGroup(), e -> convert(e));
		details.addAll(sbm);
		// 乐城
		List<BiDetail> ld = map(summaryPassengerService.findAllByGroup(), e -> convert(e));
		details.addAll(ld);
		// 根据buildingCode分组
		Map<String, List<BiDetail>> map = details.stream().collect(Collectors.groupingBy(BiDetail::getMallHdCode));
		// 遍历存入redis
		for (String mallHdCode : map.keySet()) {
			redisService.set2RedisTwoDays(RedisConstant.getKey(BiDetail.class, mallHdCode), JSON.toJSONString(map.get(mallHdCode)));
		}
	}

	private BiDetail convert(SalesreportSummarydata e) {
		return new BiDetail(e.getPk().getYyyymmdd(), e.getPk().getBuildingCode().substring(0, 6),
				e.getSalesTy(), e.getUpTy(), e.getVehicleInTy());
	}

	private BiDetail convert(SummaryPassenger e) {
		return new BiDetail(e.getPk().getYyyymmdd(), e.getPk().getMallCode(), e.getInSum());
	}


}
