package com.sbm.module.onlineleasing.file.upload.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FileUploadDetail {

	@ApiModelProperty(value = "用户编码")
	private String userCode;

	@ApiModelProperty(value = "容器名称")
	private String containerName;

	@ApiModelProperty(value = "前缀")
	private String prefix;


	public FileUploadDetail() {
	}

	public FileUploadDetail(String userCode, String containerName, String prefix) {

		this.userCode = userCode;
		this.containerName = containerName;
		this.prefix = prefix;
	}
}
