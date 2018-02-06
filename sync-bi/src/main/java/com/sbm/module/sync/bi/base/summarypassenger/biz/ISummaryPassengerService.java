package com.sbm.module.sync.bi.base.summarypassenger.biz;

import com.sbm.module.common.data.biz.IJpaService;
import com.sbm.module.sync.bi.base.summarypassenger.domain.SummaryPassenger;
import com.sbm.module.sync.bi.base.summarypassenger.domain.SummaryPassengerPK;

import java.util.List;

public interface ISummaryPassengerService extends IJpaService<SummaryPassenger, SummaryPassengerPK> {

	List<SummaryPassenger> findAllByGroup();

}
