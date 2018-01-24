package com.sbm.module.sync.bi.base.salesreport.biz;

import com.sbm.module.common.data.biz.IDataService;
import com.sbm.module.sync.bi.base.salesreport.domain.SalesreportSummarydata;
import com.sbm.module.sync.bi.base.salesreport.domain.SalesreportSummarydataPK;

public interface ISalesreportSummarydataService extends IDataService<SalesreportSummarydata, SalesreportSummarydataPK> {

	Iterable<SalesreportSummarydata> findAllByGroup();

}
