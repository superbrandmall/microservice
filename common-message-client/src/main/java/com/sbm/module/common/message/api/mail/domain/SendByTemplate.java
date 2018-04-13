package com.sbm.module.common.message.api.mail.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SendByTemplate extends Mail {

	@ApiModelProperty(value = "模板编号")
	private String code;

	@ApiModelProperty(value = "模型")
	private Object model;

	public SendByTemplate(String to, String subject, String message, Date date, String code, Object model) {
		super(to, subject, message, date);
		this.code = code;
		this.model = model;
	}
}
