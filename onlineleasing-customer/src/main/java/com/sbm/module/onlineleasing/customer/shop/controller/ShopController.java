package com.sbm.module.onlineleasing.customer.shop.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.shop.biz.IShopService;
import com.sbm.module.onlineleasing.domain.shop.ShopMaxInfo;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/shop")
public class ShopController extends BaseController {

	@Autowired
	private IShopService service;

	@ApiOperation(value = "铺位信息", notes = "铺位信息")
	@RequestMapping(value = "/{shopCode}", method = RequestMethod.GET)
	public JsonContainer<ShopMaxInfo> findOneByShopCodeAndUserCode(@PathVariable @NotBlank String shopCode, @RequestParam(required = false) String userCode) {
		return setSuccessMessage(service.findOneByShopCodeAndUserCode(shopCode, userCode));
	}

}
