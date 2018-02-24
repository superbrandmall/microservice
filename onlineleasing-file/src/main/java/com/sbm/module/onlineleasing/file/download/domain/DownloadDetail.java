package com.sbm.module.onlineleasing.file.download.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DownloadDetail {

	@ApiModelProperty(value = "大小")
	private Long size;

	@ApiModelProperty(value = "原始名称")
	private String originalFilename;

	@ApiModelProperty(value = "后缀")
	private String suffix;

	public DownloadDetail(Long size, String originalFilename, String suffix) {
		this.size = size;
		this.originalFilename = originalFilename;
		this.suffix = suffix;
	}

	public DownloadDetail() {

	}
}
