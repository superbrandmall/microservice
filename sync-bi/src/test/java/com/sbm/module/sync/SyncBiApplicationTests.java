package com.sbm.module.sync;

import com.alibaba.fastjson.JSON;
import com.sbm.module.sync.bi.base.salesreport.biz.ISalesreportSummarydataService;
import com.sbm.module.sync.bi.base.salesreport.domain.SalesreportSummarydata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SyncBiApplicationTests {

	@Autowired
	private ISalesreportSummarydataService salesreportSummarydataService;

	@Test
	public void contextLoads() {

		Iterable<SalesreportSummarydata> salesreportSummarydatas = salesreportSummarydataService.findAllByGroup();

		System.out.println(JSON.toJSONString(salesreportSummarydatas));

	}

}
