package com.sbm.module.onlineleasing.customer.base.modality.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.base.modality.biz.IModalityService;
import com.sbm.module.onlineleasing.domain.base.modality.Modality;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/base/modality")
public class ModalityController extends BaseController {

	@Autowired
	private IModalityService service;

	@ApiOperation(value = "查询业态列表", notes = "查询业态列表")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public JsonContainer<List<Modality>> findOneByMallCodeAndDescription() {
		return setSuccessMessage(service.findAll());
	}
}
