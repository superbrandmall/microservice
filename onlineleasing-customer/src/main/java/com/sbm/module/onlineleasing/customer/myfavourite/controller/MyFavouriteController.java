package com.sbm.module.onlineleasing.customer.myfavourite.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.myfavourite.biz.IMyFavouriteService;
import com.sbm.module.onlineleasing.domain.myfavourite.MyFavourite;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/myfavourite")
public class MyFavouriteController extends BaseController {

	@Autowired
	private IMyFavouriteService service;

	@ApiOperation(value = "关注铺位明细", notes = "关注铺位明细")
	@RequestMapping(value = "/details/{userCode}", method = RequestMethod.GET)
	public JsonContainer<Page<MyFavourite>> details(@PathVariable @NotBlank String userCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.getDetails(userCode, pageable));
	}

	@ApiOperation(value = "关注", notes = "关注")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer save(@RequestParam @NotBlank String userCode, @RequestParam @NotBlank String shopCode) {
		service.save(userCode, shopCode);
		return setSuccessMessage();
	}

	@ApiOperation(value = "取关", notes = "取关")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public JsonContainer delete(@RequestParam @NotBlank String userCode, @RequestParam @NotBlank String shopCode) {
		service.delete(userCode, shopCode);
		return setSuccessMessage();
	}
}
