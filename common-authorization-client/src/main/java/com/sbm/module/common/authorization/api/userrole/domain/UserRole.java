package com.sbm.module.common.authorization.api.userrole.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class UserRole {

	@ApiModelProperty(value = "用户编号")
	@NotBlank
	private String userCode;

	@ApiModelProperty(value = "角色编号")
	@NotBlank
	private String roleCode;

	public UserRole(String userCode, String roleCode) {
		this.userCode = userCode;
		this.roleCode = roleCode;
	}

	public UserRole() {

	}
}
