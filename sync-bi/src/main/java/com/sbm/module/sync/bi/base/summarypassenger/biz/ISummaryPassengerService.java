package com.sbm.module.sync.bi.base.summarypassenger.biz;

import com.sbm.module.common.data.biz.IDataService;
import com.sbm.module.sync.bi.base.summarypassenger.domain.SummaryPassenger;
import com.sbm.module.sync.bi.base.summarypassenger.domain.SummaryPassengerPK;

public interface ISummaryPassengerService extends IDataService<SummaryPassenger, SummaryPassengerPK> {

	Iterable<SummaryPassenger> findAllByGroup();

}
