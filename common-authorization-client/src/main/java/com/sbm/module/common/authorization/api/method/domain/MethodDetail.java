package com.sbm.module.common.authorization.api.method.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MethodDetail {

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

	public MethodDetail() {
	}

	public MethodDetail(String applicationName, String method, String pattern, String remark) {

		this.applicationName = applicationName;
		this.method = method;
		this.pattern = pattern;
		this.remark = remark;
	}
}
