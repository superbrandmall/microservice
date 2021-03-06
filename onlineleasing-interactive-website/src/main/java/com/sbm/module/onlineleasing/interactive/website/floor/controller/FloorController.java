package com.sbm.module.onlineleasing.interactive.website.floor.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.interactive.website.floor.biz.IFloorService;
import com.sbm.module.onlineleasing.interactive.website.floor.domain.Floor;
import com.sbm.module.onlineleasing.interactive.website.floor.domain.FloorQuery;
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
@RequestMapping("/api/floor")
public class FloorController extends BaseController {

	@Autowired
	private IFloorService service;

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "page", value = "页数", dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "每页条数", dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "sort", value = "排序", dataType = "string", paramType = "query")
	})
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	public JsonContainer<Page<Floor>> findAll(@RequestBody FloorQuery query, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(query, pageable));
	}

}
