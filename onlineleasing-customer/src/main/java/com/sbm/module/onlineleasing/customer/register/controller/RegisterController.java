package com.sbm.module.onlineleasing.customer.register.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.register.biz.IRegisterService;
import com.sbm.module.onlineleasing.domain.brand.ExistingBrand;
import com.sbm.module.onlineleasing.domain.brand.NewBrand;
import com.sbm.module.onlineleasing.domain.register.*;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/register")
public class RegisterController extends BaseController {

	@Autowired
	private IRegisterService service;

	/******************** 注册第一步 ********************/

	@ApiOperation(value = "注册第一步", notes = "注册第一步")
	@RequestMapping(value = "/step/one", method = RequestMethod.POST)
	public JsonContainer<StepOneResult> stepOne(@RequestBody @Validated StepOne vo, HttpServletResponse response) {
		return setSuccessMessage(service.stepOne(vo, response));
	}

	/******************** 注册第二步 ********************/

	@ApiOperation(value = "注册第二步", notes = "注册第二步")
	@RequestMapping(value = "/step/two", method = RequestMethod.POST)
	public JsonContainer<StepTwoResult> stepTwo(@RequestBody @Validated StepTwo vo) {
		return setSuccessMessage(service.stepTwo(vo));
	}

	@ApiOperation(value = "注册第二步商户校验", notes = "注册第二步商户校验")
	@RequestMapping(value = "/step/two/merchant/check", method = RequestMethod.POST)
	public JsonContainer<StepTwoMerchantCheckResult> stepTwoMerchantCheck(@RequestParam @NotBlank String uscc, @RequestParam @NotBlank String merchantName) {
		return setSuccessMessage(service.stepTwoMerchantCheck(uscc, merchantName));
	}

	/******************** 注册第三步 ********************/

	@ApiOperation(value = "注册第三步添加新品牌", notes = "注册第三步添加新品牌")
	@RequestMapping(value = "/step/three/addNewBrand", method = RequestMethod.POST)
	public JsonContainer<StepThreeResult> stepThreeAddNewBrand(@RequestBody @Validated StepThree<NewBrand> vo) {
		return setSuccessMessage(service.stepThreeAddNewBrand(vo));
	}

	@ApiOperation(value = "注册第三步添加已有品牌", notes = "注册第三步添加已有品牌")
	@RequestMapping(value = "/step/three/addExistingBrand", method = RequestMethod.POST)
	public JsonContainer<StepThreeResult> stepThreeAddExistingBrand(@RequestBody @Validated StepThree<ExistingBrand> vo) {
		return setSuccessMessage(service.stepThreeAddExistingBrand(vo));
	}

}
