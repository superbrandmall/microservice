package com.sbm.module.sync.hd.api.merchant.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.sync.hd.api.merchant.biz.IMerchantService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CreateApiDocs
@RestController
@RequestMapping("/api/merchant")
public class MerchantController extends BaseController {

	@Autowired
	private IMerchantService service;

	@ApiOperation(value = "刷新数据", notes = "刷新数据")
	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public JsonContainer refresh() {
		service.refresh();
		return setSuccessMessage();
	}

}
