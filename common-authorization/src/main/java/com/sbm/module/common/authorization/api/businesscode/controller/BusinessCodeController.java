package com.sbm.module.common.authorization.api.businesscode.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.authorization.api.businesscode.biz.IBusinessCodeRegisterService;
import com.sbm.module.common.authorization.api.businesscode.biz.IBusinessCodeService;
import com.sbm.module.common.authorization.api.businesscode.domain.BusinessCode;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public JsonContainer<Page<BusinessCode>> findAll(@PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(pageable));
	}

	@ApiOperation(value = "刷新数据", notes = "刷新数据")
	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public JsonContainer refresh() {
		service.refresh();
		return setSuccessMessage();
	}

	@ApiOperation(value = "根据业务类和业务代码查询", notes = "根据业务类和业务代码查询")
	@RequestMapping(value = "/findOneByBusinessClazzAndBusinessCode", method = RequestMethod.GET)
	public JsonContainer<BusinessCode> findOneByBusinessClazzAndBusinessCode(@RequestParam @NotBlank String businessClazz, @RequestParam @NotBlank String businessCode) {
		return setSuccessMessage(registerService.findOneByBusinessClazzAndBusinessCode(businessClazz, businessCode));
	}

}
