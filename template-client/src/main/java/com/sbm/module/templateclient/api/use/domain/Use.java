package com.sbm.module.templateclient.api.use.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Use {

	@ApiModelProperty(value="模板名称")
	private String name;

	@ApiModelProperty(value="模型")
	private Object model;

	@ApiModelProperty(value="结果")
	private String result;

}
