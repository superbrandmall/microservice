package com.sbm.module.sync.bi.api.bi.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.sync.bi.api.bi.biz.IBiService;
import com.sbm.module.sync.bi.api.bi.domain.Bi;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CreateApiDocs
@RestController
@RequestMapping("/api/bi")
public class BiController extends BaseController{

	@Autowired
	private IBiService service;

	@ApiOperation(value="根据buildingCode查询", notes="根据buildingCode查询")
	@RequestMapping(value = "/findByBuildingCode", method = RequestMethod.POST)
	@ResponseBody
	public JsonContainer<Bi> findByBuildingCode(@RequestBody Bi vo) {
		service.findByBuildingCode(vo);
		return setSuccessMessage(vo);
	}

	@ApiOperation(value="刷新bi数据", notes="刷新bi数据")
	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	@ResponseBody
	public JsonContainer refresh() {
		service.refresh();
		return setSuccessMessage();
	}

}
