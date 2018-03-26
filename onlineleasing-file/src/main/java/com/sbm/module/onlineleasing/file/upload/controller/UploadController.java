package com.sbm.module.onlineleasing.file.upload.controller;

import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.file.upload.biz.IUploadService;
import com.sbm.module.onlineleasing.file.upload.domain.Upload;
import com.sbm.module.onlineleasing.file.upload.domain.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/upload")
public class UploadController extends BaseController {

	@Autowired
	private IUploadService service;

	@RequestMapping(value = "/multi", method = RequestMethod.POST)
	@ResponseBody
	public JsonContainer<List<UploadResult>> multi(Upload upload) {
		return setSuccessMessage(service.upload(upload));
	}

}
