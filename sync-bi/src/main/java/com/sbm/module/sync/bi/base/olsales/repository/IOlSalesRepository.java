package com.sbm.module.sync.bi.base.olsales.repository;

import com.sbm.module.common.data.repository.IDataRepository;
import com.sbm.module.sync.bi.base.olsales.domain.OlSales;
import com.sbm.module.sync.bi.base.olsales.domain.OlSalesPK;

import java.util.List;

public interface IOlSalesRepository extends IDataRepository<OlSales, OlSalesPK> {

	List<OlSales> findAllByPk_BuildunitAndPk_BrandNameAndPk_YyyymmddBetween(String buildunit, String brandName, String startdate, String enddate);

}
