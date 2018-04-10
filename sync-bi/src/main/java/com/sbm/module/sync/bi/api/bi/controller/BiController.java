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

	@ApiOperation(value = "根据mallHdCode查询", notes = "根据mallHdCode查询")
	@RequestMapping(value = "/findByMallHdCode/{mallHdCode}", method = RequestMethod.GET)
	public JsonContainer<List<BiDetail>> findByMallHdCode(@PathVariable String mallHdCode) {
		return setSuccessMessage(service.findByMallHdCode(mallHdCode));
	}

	@ApiOperation(value = "刷新数据", notes = "刷新数据")
	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public JsonContainer refresh() {
		service.refresh();
		return setSuccessMessage();
	}

}
