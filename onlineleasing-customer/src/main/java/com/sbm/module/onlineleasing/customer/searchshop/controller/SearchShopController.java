package com.sbm.module.onlineleasing.customer.searchshop.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.searchshop.biz.ISearchShopService;
import com.sbm.module.onlineleasing.domain.searchshop.SearchShop;
import com.sbm.module.onlineleasing.domain.searchshop.SearchShopResult;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

	@ApiOperation(value = "查询历史记录", notes = "查询历史记录")
	@RequestMapping(value = "/histories/{userCode}", method = RequestMethod.GET)
	public JsonContainer<Page<SearchShop>> histories(@PathVariable @NotBlank String userCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.getHistories(userCode, pageable));
	}

}
