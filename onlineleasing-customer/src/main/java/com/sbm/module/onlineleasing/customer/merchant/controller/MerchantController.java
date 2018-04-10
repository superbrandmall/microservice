package com.sbm.module.onlineleasing.customer.merchant.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.merchant.biz.IMerchantService;
import com.sbm.module.onlineleasing.domain.merchant.Merchant;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/merchant")
public class MerchantController extends BaseController {

	@Autowired
	private IMerchantService service;

	@ApiOperation(value = "通过商户编号查询", notes = "通过商户编号查询")
	@RequestMapping(value = "/findOneByCode/{code}", method = RequestMethod.GET)
	public JsonContainer<Merchant> findOneByCode(@PathVariable @NotBlank String code) {
		return setSuccessMessage(service.findOneByCode(code));
	}

}
