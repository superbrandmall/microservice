package com.sbm.module.onlineleasing.admin.shop.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.admin.shop.biz.IShopService;
import com.sbm.module.onlineleasing.domain.searchshop.SearchShopMinInfo;
import com.sbm.module.onlineleasing.domain.shop.Shop;
import com.sbm.module.onlineleasing.domain.shop.ShopCheck;
import com.sbm.module.onlineleasing.domain.shop.ShopMaxInfo;
import com.sbm.module.onlineleasing.domain.shop.ShopQuery;
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
@RequestMapping("/api/shop")
public class ShopController extends BaseController {

	@Autowired
	private IShopService service;

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	public JsonContainer<Page<Shop>> findAll(@RequestBody ShopQuery query, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(query, pageable));
	}

	@ApiOperation(value = "铺位信息", notes = "铺位信息")
	@RequestMapping(value = "/{shopCode}", method = RequestMethod.GET)
	public JsonContainer<ShopMaxInfo> findOneByShopCode(@PathVariable @NotBlank String shopCode) {
		return setSuccessMessage(service.findOneByShopCode(shopCode));
	}

	@ApiOperation(value = "保存铺位信息", notes = "保存铺位信息")
	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public JsonContainer save(@RequestBody @Validated ShopMaxInfo shopMaxInfo) {
		service.save(shopMaxInfo);
		return setSuccessMessage();
	}

	@ApiOperation(value = "查询铺位并检查", notes = "查询铺位并检查")
	@RequestMapping(value = "/findAllBySearchShopAndCheck", method = RequestMethod.POST)
	public JsonContainer<List<ShopCheck>> findAllBySearchShopAndCheck(@RequestBody @Validated SearchShopMinInfo searchShopMinInfo) {
		return setSuccessMessage(service.findAllBySearchShopAndCheck(searchShopMinInfo));
	}
}
