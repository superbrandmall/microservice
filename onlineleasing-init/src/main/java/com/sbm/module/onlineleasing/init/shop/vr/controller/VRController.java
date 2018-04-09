package com.sbm.module.onlineleasing.init.shop.vr.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.init.shop.vr.biz.IVRService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/vr")
public class VRController extends BaseController {

	@Autowired
	private IVRService service;

	@ApiOperation(value = "初始化VR", notes = "初始化VR")
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	public JsonContainer init() {
		service.init();
		return setSuccessMessage();
	}
}
