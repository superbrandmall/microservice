package com.sbm.module.onlineleasing.init.businesscode.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.init.businesscode.biz.IBusinessCodeService;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/businesscode")
public class BusinessCodeController extends BaseController {

	@Autowired
	private IBusinessCodeService service;

	@ApiOperation(value = "初始化业务编号", notes = "初始化业务编号")
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	public JsonContainer init(@RequestParam @NotBlank String path) {
		service.init(path);
		return setSuccessMessage();
	}
}
