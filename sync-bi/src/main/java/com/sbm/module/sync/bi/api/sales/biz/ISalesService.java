package com.sbm.module.sync.bi.api.sales.biz;

import com.sbm.module.sync.bi.api.sales.domain.Sales;
import com.sbm.module.sync.bi.api.sales.domain.SalesQuery;
import com.sbm.module.sync.bi.api.sales.domain.SalesSum;

import java.util.List;

public interface ISalesService {

	Sales findOne(Sales sales);

	List<Sales> findAllByBuildunitAndBrandNameAndyyyymmddBetween(SalesQuery query);

	SalesSum calSales(SalesQuery query);
}
