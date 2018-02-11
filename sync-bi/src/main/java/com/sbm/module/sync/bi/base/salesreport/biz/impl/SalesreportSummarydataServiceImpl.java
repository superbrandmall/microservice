package com.sbm.module.sync.bi.base.salesreport.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.sync.bi.base.salesreport.biz.ISalesreportSummarydataService;
import com.sbm.module.sync.bi.base.salesreport.domain.SalesreportSummarydata;
import com.sbm.module.sync.bi.base.salesreport.domain.SalesreportSummarydataPK;
import com.sbm.module.sync.bi.base.salesreport.repository.ISalesreportSummarydataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesreportSummarydataServiceImpl extends DataServiceImpl<SalesreportSummarydata, SalesreportSummarydataPK> implements ISalesreportSummarydataService {

	@Autowired
	private ISalesreportSummarydataRepository repository;

	@Override
	public List<SalesreportSummarydata> findAllByGroup() {
		return repository.findAllByGroup();
	}
}
