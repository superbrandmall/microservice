package com.sbm.module.sync.bi.base.summarypassenger.biz;

import com.sbm.module.common.data.biz.IDataService;
import com.sbm.module.sync.bi.base.summarypassenger.domain.SummaryPassenger;
import com.sbm.module.sync.bi.base.summarypassenger.domain.SummaryPassengerPK;

import java.util.List;

public interface ISummaryPassengerService extends IDataService<SummaryPassenger, SummaryPassengerPK> {

	List<SummaryPassenger> findAllByGroup();

}
