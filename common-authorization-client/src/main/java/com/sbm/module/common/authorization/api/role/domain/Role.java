package com.sbm.module.common.authorization.api.role.domain;

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

	public Role() {
	}

	public Role(String code, String role, String roleName, String remark) {

		this.code = code;
		this.role = role;
		this.roleName = roleName;
		this.remark = remark;
	}
}
