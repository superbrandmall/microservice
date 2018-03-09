package com.sbm.module.common.authorization.api.serialcode.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.authorization.api.serialcode.biz.ISerialCodeService;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
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
@RequestMapping("/api/serialcode")
public class SerialCodeController extends BaseController {

	@Autowired
	private ISerialCodeService service;

	@ApiOperation(value = "获取下一编号", notes = "获取下一编号")
	@RequestMapping(value = "/next/{serialGroup}", method = RequestMethod.GET)
	public JsonContainer<String> next(@PathVariable String serialGroup) {
		return setSuccessMessage(service.next(serialGroup));
	}

}
