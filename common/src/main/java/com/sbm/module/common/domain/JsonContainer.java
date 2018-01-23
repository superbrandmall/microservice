package com.sbm.module.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JsonContainer<T> {

	@ApiModelProperty(value="代码")
	private String code;

	@ApiModelProperty(value="消息")
	private String message;

	@ApiModelProperty(value="数据")
	private T data;

}
