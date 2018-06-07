package com.sbm.module.common.authorization.api.businesscode.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.authorization.api.businesscode.biz.IBusinessCodeRegisterService;
import com.sbm.module.common.authorization.api.businesscode.biz.IBusinessCodeService;
import com.sbm.module.common.authorization.api.businesscode.domain.BusinessCode;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/businesscode")
public class BusinessCodeController extends BaseController {

	@Autowired
	private IBusinessCodeRegisterService registerService;

	@Autowired
	private IBusinessCodeService service;

	@ApiOperation(value = "注册业务代码", notes = "注册业务代码")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public JsonContainer<List<BusinessCode>> register(@RequestBody List<BusinessCode> vos) {
		registerService.register(vos);
		return setSuccessMessage(vos);
	}

}
