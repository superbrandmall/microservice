package com.sbm.module.onlineleasing.file.upload.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Upload {

	private FileUploadDetail vo;

	private MultipartFile[] files;

}
