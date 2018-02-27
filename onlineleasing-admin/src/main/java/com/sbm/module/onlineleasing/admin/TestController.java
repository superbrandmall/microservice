package com.sbm.module.onlineleasing.admin;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CreateApiDocs
@RestController
@RequestMapping("/api/test")
public class TestController extends BaseController {

	@Value("${eureka.instance.ip-address}")
	private String ipAddress;

	@Value("${server.port}")
	private String port;

	@ApiOperation(value = "test", notes = "test")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public JsonContainer<String> refresh() {
		System.out.println(1/0);
		return setSuccessMessage(ipAddress + ": " + port);
	}

}
