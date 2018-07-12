package com.sbm.module.onlineleasing.admin.floor.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.admin.floor.biz.IFloorService;
import com.sbm.module.onlineleasing.domain.floor.Floor;
import com.sbm.module.onlineleasing.domain.floor.FloorMaxInfo;
import com.sbm.module.onlineleasing.domain.floor.FloorQuery;
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
@RequestMapping("/api/floor")
public class FloorController extends BaseController {

	@Autowired
	private IFloorService service;

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	public JsonContainer<Page<Floor>> findAll(@RequestBody FloorQuery query, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(query, pageable));
	}

	@ApiOperation(value = "楼层信息", notes = "楼层信息")
	@RequestMapping(value = "/{floorCode}", method = RequestMethod.GET)
	public JsonContainer<FloorMaxInfo> findOneByFloorCode(@PathVariable @NotBlank String floorCode) {
		return setSuccessMessage(service.findOneByFloorCode(floorCode));
	}

	@ApiOperation(value = "保存楼层信息", notes = "保存楼层信息")
	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public JsonContainer save(@RequestBody @Validated FloorMaxInfo floorMaxInfo) {
		service.save(floorMaxInfo);
		return setSuccessMessage();
	}

}
