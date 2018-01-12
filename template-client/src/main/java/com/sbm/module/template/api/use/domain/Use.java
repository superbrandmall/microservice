package com.sbm.module.template.api.use.domain;

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

	public Use() {
	}

	public Use(String name, Object model) {
		this.name = name;
		this.model = model;
	}
}
