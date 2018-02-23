package com.sbm.module.onlineleasing.file.upload.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CreateApiDocs
@RestController
@RequestMapping("/api/upload")
public class UploadController extends BaseController {
//
//	@Autowired
//	private IUploadService service;

	@ApiOperation(value = "刷新数据", notes = "刷新数据")
	@RequestMapping(value = "/multi", method = RequestMethod.POST)
	public JsonContainer multi(@RequestParam("photo") MultipartFile photo) {
		if (photo != null) {   // 表示现在已经有文件上传了
			System.out.println("【*** UploadRest ***】文件名称："
					+ photo.getOriginalFilename() + "、文件大小：" + photo.getSize());
		}
		return setSuccessMessage();
	}

}
