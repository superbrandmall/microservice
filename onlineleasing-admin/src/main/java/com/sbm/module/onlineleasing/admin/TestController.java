package com.sbm.module.onlineleasing.admin;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@Validated
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
	public JsonContainer<String> test() {
		if (1 == 1) {
			throw new RuntimeException("eee");
		}
		return setSuccessMessage(ipAddress + ": " + port);
	}


	@ApiOperation(value = "test123", notes = "test")
	@RequestMapping(value = "/test123/{id}", method = RequestMethod.GET)
	public JsonContainer<String> test123(@Min(value = 100) @PathVariable Long id) {
		System.out.println(id);
		return setSuccessMessage(ipAddress + ": " + port);
	}

	@ApiOperation(value = "test456", notes = "test")
	@RequestMapping(value = "/test456", method = RequestMethod.GET)
	public JsonContainer<String> test456(@Min(value = 100) @RequestParam Long id, @NotBlank @RequestParam(required = false) String ttt) {
		System.out.println(id);
		return setSuccessMessage(ipAddress + ": " + port);
	}

	@ApiOperation(value = "test789", notes = "test")
	@RequestMapping(value = "/test789", method = RequestMethod.POST)
	public JsonContainer<String> test789(@Validated @RequestBody TestDomain test) {
		System.out.println(JSON.toJSONString(test));
		return setSuccessMessage(ipAddress + ": " + port);
	}
}
