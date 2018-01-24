package com.sbm.module.sync.bi.base.summarypassenger.biz;

import com.sbm.module.common.data.biz.IDataService;
import com.sbm.module.sync.bi.base.summarypassenger.domain.SummaryPassenger;

public interface ISummaryPassengerService extends IDataService<SummaryPassenger, Integer> {

	Iterable<SummaryPassenger> findAllByGroup();

}
