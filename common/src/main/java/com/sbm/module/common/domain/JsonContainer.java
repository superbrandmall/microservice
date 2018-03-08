package com.sbm.module.common.domain;

import com.sbm.module.common.exception.IBusinessCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JsonContainer<T> implements IBusinessCode {

	@ApiModelProperty(value = "错误类")
	private String clazz;

	@ApiModelProperty(value = "代码")
	private String code;

	@ApiModelProperty(value = "系统信息")
	private String message;

	@ApiModelProperty(value = "顾客展示信息")
	private String customerMessage;

	@ApiModelProperty(value = "数据")
	private T data;

	public void set(IBusinessCode businessCode) {
		this.clazz = businessCode.getClazz();
		this.code = businessCode.getCode();
		this.message = businessCode.getMessage();
		this.customerMessage = businessCode.getCustomerMessage();
		if (null != businessCode.getData()) {
			this.data = (T) businessCode.getData();
		}
	}

}
