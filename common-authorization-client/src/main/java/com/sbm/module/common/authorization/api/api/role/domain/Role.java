package com.sbm.module.common.authorization.api.api.role.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Role {

	@ApiModelProperty(value = "编号")
	private String code;

	@ApiModelProperty(value = "角色")
	private String role;

	@ApiModelProperty(value = "角色名称")
	private String roleName;

	@ApiModelProperty(value = "备注")
	private String remark;

}
