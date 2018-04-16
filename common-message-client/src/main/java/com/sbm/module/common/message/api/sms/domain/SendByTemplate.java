package com.sbm.module.common.message.api.sms.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SendByTemplate extends SMS {

	@ApiModelProperty(value = "模板编号")
	private String code;

	@ApiModelProperty(value = "模型")
	private Object model;

	public SendByTemplate(String to, String message, Date date, String code, Object model) {
		super(to, message, date);
		this.code = code;
		this.model = model;
	}

	public SendByTemplate() {

	}
}
