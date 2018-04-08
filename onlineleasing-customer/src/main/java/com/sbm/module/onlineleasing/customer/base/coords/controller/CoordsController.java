package com.sbm.module.onlineleasing.customer.base.coords.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.base.coords.biz.ICoordsService;
import com.sbm.module.onlineleasing.domain.base.coords.Coords;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/base/coords")
public class CoordsController extends BaseController {

	@Autowired
	private ICoordsService service;

	@ApiOperation(value = "根据mallCode, description查询坐标信息", notes = "根据mallCode, description查询楼层信息")
	@RequestMapping(value = "/{mallCode}/{description}", method = RequestMethod.GET)
	public JsonContainer<List<Coords>> findAllByMallCodeAndDescription(@PathVariable String mallCode, @PathVariable String description) {
		return setSuccessMessage(service.findAllByMallCodeAndDescription(mallCode, description));
	}
}
