package com.sbm.module.onlineleasing.file.upload.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UploadResult {

	@ApiModelProperty(value = "用户编码")
	private String userCode;

	@ApiModelProperty(value = "uri")
	private String uri;

	@ApiModelProperty(value = "容器名称")
	private String containerName;

	@ApiModelProperty(value = "前缀")
	private String prefix;

	@ApiModelProperty(value = "文件id")
	private String fileId;

	@ApiModelProperty(value = "大小")
	private Long size;

	@ApiModelProperty(value = "原始名称")
	private String originalFilename;

	@ApiModelProperty(value = "后缀")
	private String suffix;

	public UploadResult() {
	}

	public UploadResult(String userCode, String uri, String containerName, String prefix, String fileId, Long size, String originalFilename, String suffix) {

		this.userCode = userCode;
		this.uri = uri;
		this.containerName = containerName;
		this.prefix = prefix;
		this.fileId = fileId;
		this.size = size;
		this.originalFilename = originalFilename;
		this.suffix = suffix;
	}
}
