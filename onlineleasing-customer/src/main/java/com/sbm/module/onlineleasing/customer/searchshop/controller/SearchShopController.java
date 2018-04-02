package com.sbm.module.onlineleasing.customer.searchshop.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.searchshop.biz.ISearchShopService;
import com.sbm.module.onlineleasing.domain.searchshop.SearchShop;
import com.sbm.module.onlineleasing.domain.searchshop.SearchShopResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/searchshop")
public class SearchShopController extends BaseController {

	@Autowired
	private ISearchShopService service;

	@ApiOperation(value = "查询铺位", notes = "查询铺位")
	@RequestMapping(value = "/details", method = RequestMethod.POST)
	public JsonContainer<SearchShopResult> details(@RequestBody @Validated SearchShop searchShop) {
		return setSuccessMessage(service.getDetails(searchShop));
	}
}
