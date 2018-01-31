package com.sbm.module.partner.hd.api.building.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.partner.hd.api.building.biz.IHdBuildingService;
import com.sbm.module.partner.hd.api.building.domain.HdBuilding;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CreateApiDocs
@RestController
@RequestMapping("/api/hdBuilding")
public class HdBuildingController extends BaseController{

	@Autowired
	private IHdBuildingService service;

	@ApiOperation(value="查询所有HdBuilding", notes="查询所有HdBuilding")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	@ResponseBody
	public JsonContainer<List<HdBuilding>> findAll() {
		return setSuccessMessage(service.findAll());
	}

}
