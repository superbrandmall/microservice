package com.sbm.module.partner.hd.api.biztype.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.partner.hd.api.biztype.biz.IHdBiztypeService;
import com.sbm.module.partner.hd.api.biztype.domain.HdBiztype;
import com.sbm.module.partner.hd.api.building.domain.HdBuilding;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CreateApiDocs
@RestController
@RequestMapping("/api/hdBiztype")
public class HdBiztypeController extends BaseController {

	@Autowired
	private IHdBiztypeService service;

	@ApiOperation(value = "查询所有结果", notes = "查询所有结果")
	@RequestMapping(value = "/findAllVo", method = RequestMethod.GET)
	public JsonContainer<List<HdBiztype>> findAllVo() {
		return setSuccessMessage(service.findAllVo());
	}

}
