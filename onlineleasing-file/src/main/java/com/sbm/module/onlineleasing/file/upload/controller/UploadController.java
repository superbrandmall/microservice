package com.sbm.module.onlineleasing.file.upload.controller;

import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.file.upload.biz.IUploadService;
import com.sbm.module.onlineleasing.file.upload.domain.FileUploadDetail;
import com.sbm.module.onlineleasing.file.upload.domain.Upload;
import com.sbm.module.onlineleasing.file.upload.domain.UploadResult;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

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

	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	@SneakyThrows
	public JsonContainer<List<UploadResult>> upload(@RequestPart MultipartFile file,
													@RequestParam(value = "userCode") String userCode,
													@RequestParam(value = "containerName") String containerName,
													@RequestParam(value = "prefix") String prefix) {
		return setSuccessMessage(service.upload(new Upload(new FileUploadDetail(userCode, containerName, prefix), new MultipartFile[]{file})));
	}

}
