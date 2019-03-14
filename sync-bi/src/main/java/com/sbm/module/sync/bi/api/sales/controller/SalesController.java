package com.sbm.module.sync.bi.api.sales.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.sync.bi.api.sales.biz.ISalesService;
import com.sbm.module.sync.bi.api.sales.domain.Sales;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
