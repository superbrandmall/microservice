package com.sbm.module.onlineleasing.interactive.website.shopimages.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.interactive.website.shopimages.biz.IShopImagesService;
import com.sbm.module.onlineleasing.interactive.website.shopimages.domain.ShopImages;
import com.sbm.module.onlineleasing.interactive.website.shopimages.domain.ShopImagesQuery;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/shopimages")
public class ShopImagesController extends BaseController {

	@Autowired
	private IShopImagesService service;

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "page", value = "页数", dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "每页条数", dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "sort", value = "排序", dataType = "string", paramType = "query")
	})
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	public JsonContainer<Page<ShopImages>> findAll(@RequestBody ShopImagesQuery query, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(query, pageable));
	}

}
