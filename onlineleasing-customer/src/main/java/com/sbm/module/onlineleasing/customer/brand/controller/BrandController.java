package com.sbm.module.onlineleasing.customer.brand.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.domain.brand.*;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/brand")
public class BrandController extends BaseController {

	@Autowired
	private IBrandService service;

	@ApiOperation(value = "通过商户编号查询全部品牌", notes = "通过商户编号查询全部品牌")
	@RequestMapping(value = "/findAllByMerchantCode/{merchantCode}", method = RequestMethod.GET)
	public JsonContainer<List<MerchantBrand>> findAllByMerchantCode(@PathVariable @NotBlank String merchantCode) {
		return setSuccessMessage(service.findAllByMerchantCode(merchantCode));
	}

	@ApiOperation(value = "通过品牌编号查询", notes = "通过品牌编号查询")
	@RequestMapping(value = "/findOneByCode/{code}", method = RequestMethod.GET)
	public JsonContainer<Brand> findOneByCode(@PathVariable @NotBlank String code) {
		return setSuccessMessage(service.findOneByCode(code));
	}

	@ApiOperation(value = "通过品牌名称模糊查询", notes = "通过品牌名称模糊查询")
	@RequestMapping(value = "/findAllByNameContaining", method = RequestMethod.GET)
	public JsonContainer<List<BrandName>> findAllByNameContaining(@RequestParam @NotBlank String name) {
		return setSuccessMessage(service.findAllByNameContaining(name));
	}

	@ApiOperation(value = "添加新品牌", notes = "添加新品牌")
	@RequestMapping(value = "/addNewBrand", method = RequestMethod.POST)
	public JsonContainer addNewBrand(@RequestBody @Validated NewBrand vo) {
		service.addNewBrand(vo);
		return setSuccessMessage();
	}

	@ApiOperation(value = "添加已有品牌", notes = "添加已有品牌")
	@RequestMapping(value = "/addExistingBrand", method = RequestMethod.POST)
	public JsonContainer addExistingBrand(@RequestBody @Validated ExistingBrand vo) {
		service.addExistingBrand(vo);
		return setSuccessMessage();
	}

}
