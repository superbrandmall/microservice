package com.sbm.module.common.domain;

import com.sbm.module.common.exception.IBusinessCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JsonContainer<T> {

	@ApiModelProperty(value = "代码")
	private String code;

	@ApiModelProperty(value = "系统信息")
	private String message;

	@ApiModelProperty(value = "顾客展示信息")
	private String customerMessage;

	@ApiModelProperty(value = "数据")
	private T data;

	public void set(IBusinessCode businessCode) {
		setCode(businessCode.getCode());
		setMessage(businessCode.getMessage());
		setCustomerMessage(businessCode.getCustomerMessage());
	}

}
