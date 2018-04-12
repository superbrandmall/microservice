package com.sbm.module.common.message.api.template.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Template {

	@ApiModelProperty(value = "模板名称")
	private String name;

	@ApiModelProperty(value = "模型")
	private Object model;

	public Template() {
	}

	public Template(String name, Object model) {
		this.name = name;
		this.model = model;
	}
}
