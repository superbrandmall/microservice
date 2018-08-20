package com.sbm.module.onlineleasing.admin.building.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.admin.building.biz.IBuildingService;
import com.sbm.module.onlineleasing.domain.building.Building;
import com.sbm.module.onlineleasing.domain.building.BuildingMaxInfo;
import com.sbm.module.onlineleasing.domain.building.BuildingQuery;
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
@RequestMapping("/api/building")
public class BuildingController extends BaseController {

	@Autowired
	private IBuildingService service;

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	public JsonContainer<Page<Building>> findAll(@RequestBody BuildingQuery query, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(query, pageable));
	}

	@ApiOperation(value = "建筑物信息", notes = "建筑物信息")
	@RequestMapping(value = "/{buildingCode}", method = RequestMethod.GET)
	public JsonContainer<BuildingMaxInfo> findOneByFloorCode(@PathVariable @NotBlank String buildingCode) {
		return setSuccessMessage(service.findOneByBuildingCode(buildingCode));
	}

	@ApiOperation(value = "保存建筑物信息", notes = "保存建筑物信息")
	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public JsonContainer save(@RequestBody @Validated BuildingMaxInfo buildingMaxInfo) {
		service.save(buildingMaxInfo);
		return setSuccessMessage();
	}

}
