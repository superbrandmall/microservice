package com.sbm.module.common.message.api.template.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Template {

	@ApiModelProperty(value = "模板编号")
	private String code;

	@ApiModelProperty(value = "模型")
	private Object model;

	public Template() {
	}

	public Template(String code, Object model) {
		this.code = code;
		this.model = model;
	}
}
