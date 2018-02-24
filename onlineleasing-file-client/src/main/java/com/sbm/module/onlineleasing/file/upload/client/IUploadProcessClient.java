package com.sbm.module.onlineleasing.file.upload.client;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.file.upload.domain.UploadDetail;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "onlineleasing-file")
@RequestMapping("/api/uploadprocess")
public interface IUploadProcessClient {

	@RequestMapping("/saveUploadDetail")
	JsonContainer<String> saveUploadDetail(@RequestBody UploadDetail vo);

}
