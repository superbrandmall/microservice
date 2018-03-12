package com.sbm.module.common.authorization.api.method.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Method {

	@ApiModelProperty(value = "编号")
	private String code;

	@ApiModelProperty(value = "工程名称")
	private String applicationName;

	@ApiModelProperty(value = "方法")
	private String method;

	@ApiModelProperty(value = "样式")
	private String pattern;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "操作")
	private String operate;

	public Method() {
	}

	public Method(String applicationName, String method, String pattern, String remark) {

		this.applicationName = applicationName;
		this.method = method;
		this.pattern = pattern;
		this.remark = remark;
	}

	public Method(String code, String applicationName, String method, String pattern, String remark) {
		this.code = code;
		this.applicationName = applicationName;
		this.method = method;
		this.pattern = pattern;
		this.remark = remark;
	}
}
