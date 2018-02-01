package com.sbm.module.partner.hd.api.mall.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.partner.hd.api.mall.biz.IHdMallService;
import com.sbm.module.partner.hd.api.mall.domain.HdMall;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CreateApiDocs
@RestController
@RequestMapping("/api/hdMall")
public class HdMallController extends BaseController{

	@Autowired
	private IHdMallService service;

	@ApiOperation(value="查询所有结果", notes="查询所有结果")
	@RequestMapping(value = "/findAllVo", method = RequestMethod.GET)
	public JsonContainer<List<HdMall>> findAllVo() {
		return setSuccessMessage(service.findAllVo());
	}

}
