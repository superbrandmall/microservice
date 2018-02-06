package com.sbm.module.sync.bi.base.salesreport.biz;

import com.sbm.module.common.data.biz.IJpaService;
import com.sbm.module.sync.bi.base.salesreport.domain.SalesreportSummarydata;
import com.sbm.module.sync.bi.base.salesreport.domain.SalesreportSummarydataPK;

import java.util.List;

public interface ISalesreportSummarydataService extends IJpaService<SalesreportSummarydata, SalesreportSummarydataPK> {

	List<SalesreportSummarydata> findAllByGroup();

}
