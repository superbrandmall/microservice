package com.sbm.module.onlineleasing.file.upload.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UploadDetail {

	@ApiModelProperty(value = "用户编号")
	private String userCode;

	@ApiModelProperty(value = "文件id")
	private String fileId;

	@ApiModelProperty(value = "容器名称")
	private String containerName;

	@ApiModelProperty(value = "编号")
	private String code;

	@ApiModelProperty(value = "类型")
	private String type;

	@ApiModelProperty(value = "用途")
	private String use;

}
