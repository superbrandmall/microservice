package com.sbm.module.onlineleasing.file.download.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Download {

	@ApiModelProperty(value = "key")
	private String key;

	@ApiModelProperty(value = "类型")
	private String type;

	@ApiModelProperty(value = "明细")
	private DownloadDetail detail;

}
