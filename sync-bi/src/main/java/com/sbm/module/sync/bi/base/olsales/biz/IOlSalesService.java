package com.sbm.module.sync.bi.base.olsales.biz;

import com.sbm.module.common.data.biz.IDataService;
import com.sbm.module.sync.bi.base.olsales.domain.OlSales;
import com.sbm.module.sync.bi.base.olsales.domain.OlSalesPK;

import java.util.List;

public interface IOlSalesService extends IDataService<OlSales, OlSalesPK> {

	List<OlSales> findAllByPk_BuildunitAndPk_BrandNameAndPk_YyyymmddBetweenOrderByPk_YyyymmddDesc(String buildunit, String brandName, String startdate, String enddate);

}
