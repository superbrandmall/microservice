package com.sbm.module.sync.bi.api.bi.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.sync.bi.api.bi.biz.IBiService;
import com.sbm.module.sync.bi.api.bi.domain.BiDetail;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CreateApiDocs
@RestController
@RequestMapping("/api/bi")
public class BiController extends BaseController {

	@Autowired
	private IBiService service;

	@ApiOperation(value = "根据mallCode查询", notes = "根据mallCode查询")
	@RequestMapping(value = "/findByMallCode/{mallCode}", method = RequestMethod.GET)
	@ResponseBody
	public JsonContainer<List<BiDetail>> findByMallCode(@PathVariable String mallCode) {
		return setSuccessMessage(service.findByMallCode(mallCode));
	}

	@ApiOperation(value = "刷新bi数据", notes = "刷新bi数据")
	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	@ResponseBody
	public JsonContainer refresh() {
		service.refresh();
		return setSuccessMessage();
	}

}
