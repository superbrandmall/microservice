package com.sbm.module.onlineleasing.file.upload.client;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.file.upload.domain.UploadResult;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(value = "onlineleasing-file", configuration = IUploadClient.MultipartSupportConfig.class)
@RequestMapping("/api/upload")
public interface IUploadClient {

	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	JsonContainer<List<UploadResult>> upload(@RequestPart MultipartFile file,
											 @RequestParam(value = "userCode") String userCode,
											 @RequestParam(value = "containerName") String containerName,
											 @RequestParam(value = "prefix") String prefix);

	@Configuration
	class MultipartSupportConfig {

		@Autowired
		private ObjectFactory<HttpMessageConverters> messageConverters;

		@Bean
		public Encoder feignEncoder() {
			return new SpringFormEncoder(new SpringEncoder(messageConverters));
		}
	}
}
