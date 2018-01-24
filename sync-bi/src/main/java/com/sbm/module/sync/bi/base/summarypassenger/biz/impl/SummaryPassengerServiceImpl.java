package com.sbm.module.sync.bi.base.summarypassenger.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.sync.bi.base.summarypassenger.biz.ISummaryPassengerService;
import com.sbm.module.sync.bi.base.summarypassenger.domain.SummaryPassenger;
import com.sbm.module.sync.bi.base.summarypassenger.domain.SummaryPassengerPK;
import com.sbm.module.sync.bi.base.summarypassenger.repository.ISummaryPassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummaryPassengerServiceImpl extends DataServiceImpl<SummaryPassenger, SummaryPassengerPK> implements ISummaryPassengerService {

	@Autowired
	private ISummaryPassengerRepository repository;

	@Override
	public Iterable<SummaryPassenger> findAllByGroup() {
		return repository.findAllByGroup();
	}
}
