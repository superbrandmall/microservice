package com.sbm.module.onlineleasing.file.upload.client;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.file.upload.config.UploadConfig;
import com.sbm.module.onlineleasing.file.upload.domain.Upload;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "onlineleasing-file", configuration = UploadConfig.class)
@RequestMapping("/api/upload")
public interface IUploadClient {

	@RequestMapping(value = "/multi", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	JsonContainer<String> saveUploadDetail(Upload upload);

}
