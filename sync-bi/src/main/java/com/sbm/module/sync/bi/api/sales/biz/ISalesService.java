package com.sbm.module.sync.bi.api.sales.biz;

import com.sbm.module.sync.bi.api.sales.domain.Sales;
import com.sbm.module.sync.bi.api.sales.domain.SalesQuery;
import com.sbm.module.sync.bi.api.sales.domain.SalesResult;
import com.sbm.module.sync.bi.api.sales.domain.SalesSum;

public interface ISalesService {

	Sales findOne(Sales sales);

	SalesResult findAllByBuildunitAndBrandNameAndyyyymmddBetween(SalesQuery query);

	SalesSum calSales(SalesQuery query);
}
