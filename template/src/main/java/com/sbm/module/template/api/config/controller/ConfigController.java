package com.sbm.module.template.api.config.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.template.api.config.biz.IConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CreateApiDocs
@RestController
@RequestMapping("/api/config")
public class ConfigController extends BaseController{

	@Autowired
	private IConfigService service;

	@ApiOperation(value="刷新template缓存", notes="刷新template缓存")
	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public JsonContainer refresh() {
		service.refresh();
		return setSuccessMessage();
	}

}
