package com.sbm.module.onlineleasing.init.merchant.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.init.merchant.biz.IMerchantService;
import com.sbm.module.onlineleasing.init.merchant.domain.MerchantCheck;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/merchant")
public class MerchantController extends BaseController {

	@Autowired
	private IMerchantService service;

	@ApiOperation(value = "初始化Merchant", notes = "初始化Merchant")
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public JsonContainer<List<MerchantCheck>> init() {
		return setSuccessMessage(service.init());
	}

	@ApiOperation(value = "初始化Merchant并下载", notes = "初始化Merchant并下载", produces = "application/octet-stream")
	@RequestMapping(value = "/init/download", method = RequestMethod.GET)
	public void initDownload(HttpServletResponse response) {
		service.initDownload(response);
	}
}
