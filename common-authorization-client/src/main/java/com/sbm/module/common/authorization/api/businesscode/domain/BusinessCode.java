package com.sbm.module.common.authorization.api.businesscode.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Data
public class BusinessCode {

	@ApiModelProperty(value = "代码")
	@NotBlank
	private String code;

	@ApiModelProperty(value = "业务类")
	private String businessClazz;

	@ApiModelProperty(value = "业务代码")
	private String businessCode;

	@ApiModelProperty(value = "消息")
	private String message;

	@ApiModelProperty(value = "客户消息（默认）")
	private String customerMessage;

	@ApiModelProperty(value = "操作")
	private String operate;

	@ApiModelProperty(value = "客户消息（多语言）")
	private List<BusinessCodeLang> businessCodeLangs = new ArrayList<>();

	public BusinessCode() {
	}

	public BusinessCode(String businessClazz, String businessCode, String message, String customerMessage) {
		this.businessClazz = businessClazz;
		this.businessCode = businessCode;
		this.message = message;
		this.customerMessage = customerMessage;
	}

	public BusinessCode(String code, String businessClazz, String businessCode, String message, String customerMessage) {
		this.code = code;
		this.businessClazz = businessClazz;
		this.businessCode = businessCode;
		this.message = message;
		this.customerMessage = customerMessage;
	}
}
