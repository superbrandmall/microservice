package com.sbm.module.onlineleasing.customer.base.info.mall.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.base.info.mall.biz.IMallInfoService;
import com.sbm.module.onlineleasing.domain.base.info.mall.MallInfo;
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
@RequestMapping("/api/base/info/mall")
public class MallInfoController extends BaseController {

	@Autowired
	private IMallInfoService service;

	@ApiOperation(value = "根据mallCode查询项目信息", notes = "根据mallCode查询项目信息")
	@RequestMapping(value = "/{mallCode}", method = RequestMethod.GET)
	public JsonContainer<MallInfo> findOneByMallCodeAndDescription(@PathVariable String mallCode) {
		return setSuccessMessage(service.findOneByMallCode(mallCode));
	}
}
