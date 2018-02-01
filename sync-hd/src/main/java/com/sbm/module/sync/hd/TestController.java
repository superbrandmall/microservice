package com.sbm.module.sync.hd;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CreateApiDocs
@RestController
@RequestMapping("/api/test")
public class TestController extends BaseController {


	@ApiOperation(value = "根据mallCode查询", notes = "根据mallCode查询")
	@RequestMapping(value = "/findByMallCode/{mallCode}", method = RequestMethod.GET)
	public JsonContainer<List<String>> findByMallCode(@PathVariable String mallCode) {
		List<String> list = new ArrayList<String>();
		list.add(mallCode);
		return setSuccessMessage(list);
	}

}
