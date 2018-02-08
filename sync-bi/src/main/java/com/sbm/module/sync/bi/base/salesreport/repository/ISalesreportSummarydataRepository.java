package com.sbm.module.sync.bi.base.salesreport.repository;

import com.sbm.module.common.data.repository.IDataRepository;
import com.sbm.module.sync.bi.base.salesreport.domain.SalesreportSummarydata;
import com.sbm.module.sync.bi.base.salesreport.domain.SalesreportSummarydataPK;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISalesreportSummarydataRepository extends IDataRepository<SalesreportSummarydata, SalesreportSummarydataPK> {

	@Query(value = "select yyyymm yyyymmdd, building_code, sum(sales_ty) sales_ty, sum(up_ty) up_ty, sum(vehicle_in_ty) vehicle_in_ty from (select substr(yyyymmdd, 0, 6) yyyymm, building_code, sales_ty, up_ty, vehicle_in_ty from uedw.edw_salesreport_summarydata_f where yyyymmdd between to_char(trunc(add_months(sysdate, -12), 'month'), 'yyyyMMdd') and to_char(trunc(last_day(add_months(sysdate, -1))), 'yyyyMMdd') ) a group by yyyymm, building_code order by yyyymm", nativeQuery = true)
	List<SalesreportSummarydata> findAllByGroup();

}
