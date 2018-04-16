package com.sbm.module.common.message.api.sms.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SMS {

	@ApiModelProperty(value = "收件人")
	private String to;

	@ApiModelProperty(value = "消息")
	private String message;

	@ApiModelProperty(value = "发送时间")
	private Date date;

	public SMS() {
	}

	public SMS(String to, String message, Date date) {
		this.to = to;
		this.message = message;
		this.date = date;
	}
}
