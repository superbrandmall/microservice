package com.sbm.module.sync.bi.base.summarypassenger.repository;

import com.sbm.module.common.data.dao.IDataRepository;
import com.sbm.module.sync.bi.base.summarypassenger.domain.SummaryPassenger;
import com.sbm.module.sync.bi.base.summarypassenger.domain.SummaryPassengerPK;
import org.springframework.data.jpa.repository.Query;

public interface ISummaryPassengerRepository extends IDataRepository<SummaryPassenger, SummaryPassengerPK> {

	@Query(value = "select yyyymm yyyymmdd, site_key, mall_code, sum(in_sum) in_sum from (select substr(yyyymmdd, 0, 6) yyyymm, site_key, mall_code, in_sum  from l_uedw.edw_int_summary_passenger where site_key in ('P00001', 'P00002') and yyyymmdd between to_char(trunc(add_months(sysdate, -12), 'month'), 'yyyyMMdd') and to_char(trunc(last_day(add_months(sysdate, -1))), 'yyyyMMdd')) a group by yyyymm, site_key, mall_code order by mall_code, yyyymm", nativeQuery = true)
	Iterable<SummaryPassenger> findAllByGroup();

}
