package com.sbm.module.common.message.api.mail.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Mail {

	@ApiModelProperty(value="收件人")
	private String to;

	@ApiModelProperty(value="标题")
	private String subject;

	@ApiModelProperty(value="消息")
	private String message;

}
