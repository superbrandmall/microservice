package com.sbm.module.onlineleasing.file.upload.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.file.upload.biz.IUploadService;
import com.sbm.module.onlineleasing.file.upload.domain.UploadDetail;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CreateApiDocs
@RestController
@RequestMapping("/api/uploadprocess")
public class UploadProcessController extends BaseController {

	@Autowired
	private IUploadService service;

	@ApiOperation(value = "保存上传信息", notes = "传入参数，保存上传信息")
	@RequestMapping(value = "/saveUploadDetail", method = RequestMethod.POST)
	public JsonContainer<String> saveUploadDetail(@RequestBody UploadDetail vo) {
		return setSuccessMessage(service.saveUploadDetail(vo));
	}


}
