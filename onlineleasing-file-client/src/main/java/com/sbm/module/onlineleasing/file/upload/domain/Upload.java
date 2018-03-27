package com.sbm.module.onlineleasing.file.upload.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Upload {

	private FileUploadDetail vo;

	private MultipartFile[] files;

	public Upload() {
	}

	public Upload(FileUploadDetail vo, MultipartFile[] files) {

		this.vo = vo;
		this.files = files;
	}


}
