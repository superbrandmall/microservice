package com.sbm.module.onlineleasing.customer.brand.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.customer.brand.domain.MerchantBrand;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
