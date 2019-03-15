package com.sbm.module.sync.bi.api.sales.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.sync.bi.api.sales.biz.ISalesService;
import com.sbm.module.sync.bi.api.sales.domain.Sales;
import com.sbm.module.sync.bi.api.sales.domain.SalesQuery;
import com.sbm.module.sync.bi.api.sales.domain.SalesResult;
import com.sbm.module.sync.bi.api.sales.domain.SalesSum;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CreateApiDocs
@RestController
@RequestMapping("/api/sales")
public class SalesController extends BaseController {

	@Autowired
	private ISalesService service;

	@ApiOperation(value = "通过主键查询", notes = "通过主键查询")
	@RequestMapping(value = "/findOne", method = RequestMethod.POST)
	public JsonContainer<Sales> findOne(@RequestBody @Validated Sales sales) {
		return setSuccessMessage(service.findOne(sales));
	}

	@ApiOperation(value = "通过主键查询时间段", notes = "通过主键查询间段")
	@RequestMapping(value = "/findAllByBuildunitAndBrandNameAndyyyymmddBetween", method = RequestMethod.POST)
	public JsonContainer<SalesResult> findAllByBuildunitAndBrandNameAndyyyymmddBetween(@RequestBody @Validated SalesQuery query) {
		return setSuccessMessage(service.findAllByBuildunitAndBrandNameAndyyyymmddBetween(query));
	}

	@ApiOperation(value = "计算销售", notes = "计算销售")
	@RequestMapping(value = "/calSales", method = RequestMethod.POST)
	public JsonContainer<SalesSum> calSales(@RequestBody @Validated SalesQuery query) {
		return setSuccessMessage(service.calSales(query));
	}
}
