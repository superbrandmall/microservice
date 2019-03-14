package com.sbm.module.sync.bi.base.olsales.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.sync.bi.base.olsales.biz.IOlSalesService;
import com.sbm.module.sync.bi.base.olsales.domain.OlSales;
import com.sbm.module.sync.bi.base.olsales.domain.OlSalesPK;
import com.sbm.module.sync.bi.base.olsales.repository.IOlSalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OlSalesServiceImpl extends DataServiceImpl<OlSales, OlSalesPK> implements IOlSalesService {

	@Autowired
	private IOlSalesRepository repository;

}
