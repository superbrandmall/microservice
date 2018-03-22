package com.sbm.module.onlineleasing.customer.base.info.floor.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.base.info.floor.biz.IFloorInfoService;
import com.sbm.module.onlineleasing.domain.base.info.floor.FloorInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/base/info/floor")
public class FloorInfoController extends BaseController {

	@Autowired
	private IFloorInfoService service;

	@ApiOperation(value = "根据mallCode, description查询楼层信息", notes = "根据mallCode, description查询楼层信息")
	@RequestMapping(value = "/{mallCode}/{description}", method = RequestMethod.GET)
	public JsonContainer<FloorInfo> findOneByMallCodeAndDescription(@PathVariable String mallCode, @PathVariable String description) {
		return setSuccessMessage(service.findOneByMallCodeAndDescription(mallCode, description));
	}
}
