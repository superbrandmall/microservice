package com.sbm.module.common.authorization.api.method.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.authorization.api.method.biz.IMethodRegisterService;
import com.sbm.module.common.authorization.api.method.biz.IMethodService;
import com.sbm.module.common.authorization.api.method.domain.Method;
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
@RequestMapping("/api/method")
public class MethodController extends BaseController {

	@Autowired
	private IMethodRegisterService registerService;

	@Autowired
	private IMethodService service;

	@ApiOperation(value = "注册方法", notes = "注册方法")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public JsonContainer<List<Method>> register(@RequestBody List<Method> vos) {
		registerService.register(vos);
		return setSuccessMessage(vos);
	}

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public JsonContainer<Page<Method>> findAll(@PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(pageable));
	}

	@ApiOperation(value = "刷新数据", notes = "刷新数据")
	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public JsonContainer refresh() {
		service.refresh();
		return setSuccessMessage();
	}

	@ApiOperation(value = "根据路径和方法查询", notes = "根据路径和方法查询")
	@RequestMapping(value = "/findOneByPathAndMethod", method = RequestMethod.GET)
	public JsonContainer<Method> findOneByPathAndMethod(@RequestParam @NotBlank String path, @RequestParam @NotBlank String method) {
		return setSuccessMessage(service.findOneByPathAndMethod(path, method));
	}

	@ApiOperation(value = "根据应用名称查询", notes = "根据应用名称查询")
	@RequestMapping(value = "/findAllByApplicationName", method = RequestMethod.GET)
	public JsonContainer<List<Method>> findAllByApplicationName(@RequestParam @NotBlank String applicationName) {
		return setSuccessMessage(service.findAllByApplicationName(applicationName));
	}
}
