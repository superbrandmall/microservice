package com.sbm.module.onlineleasing.init.shop.image.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.file.upload.client.IUploadClient;
import com.sbm.module.onlineleasing.file.upload.domain.FileUploadDetail;
import com.sbm.module.onlineleasing.file.upload.domain.Upload;
import com.sbm.module.onlineleasing.file.upload.domain.UploadResult;
import com.sbm.module.onlineleasing.init.shop.image.biz.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static feign.form.util.CharsetUtil.UTF_8;

@Service
public class TestServiceImpl implements IImageService {

	@Autowired
	private IUploadClient client;

	@Override
	public void init(String path) {


		String folder = "test_folder";

//			File file = new File("C:\\Users\\zjk\\Desktop\\img\\204001\\HB01\\HB1FL001\\0.jpg");
//			MultipartFile multipartFile = new MockMultipartFile(file.getName(), new FileInputStream(file));

		MultipartFile multipartFile = new MockMultipartFile("file", "test".getBytes(UTF_8));

		String message = "test_message";

		JsonContainer<String> result = client.upload(multipartFile);

		System.out.println(JSON.toJSONString(result));


	}
}
