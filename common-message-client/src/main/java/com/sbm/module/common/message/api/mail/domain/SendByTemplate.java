package com.sbm.module.common.message.api.mail.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SendByTemplate extends Mail {

	@ApiModelProperty(value = "模板名称")
	private String name;

	@ApiModelProperty(value = "模型")
	private Object model;

}
