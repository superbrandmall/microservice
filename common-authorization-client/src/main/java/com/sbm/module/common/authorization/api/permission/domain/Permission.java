package com.sbm.module.common.authorization.api.permission.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class Permission {

	@ApiModelProperty(value = "用户编号")
	@NotBlank
	private String login;

	@ApiModelProperty(value = "口令")
	@NotBlank
	private String token;

	@ApiModelProperty(value = "路径")
	@NotBlank
	private String path;

}