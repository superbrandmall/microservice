package com.sbm.module.common.authorization.api.permission.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class Permission {

	@ApiModelProperty(value = "用户编号")
	private String login;

	@ApiModelProperty(value = "口令")
	private String token;

	@ApiModelProperty(value = "路径")
	@NotBlank
	private String path;

	@ApiModelProperty(value = "方法")
	@NotBlank
	private String method;

	public Permission(String login, String token, String path, String method) {
		this.login = login;
		this.token = token;
		this.path = path;
		this.method = method;
	}

	public Permission() {

	}
}