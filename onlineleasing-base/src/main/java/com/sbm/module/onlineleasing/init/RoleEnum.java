package com.sbm.module.onlineleasing.init;

import com.sbm.module.common.authorization.api.role.domain.Role;

public enum RoleEnum {
	ADMIN(new Role(null, "admin", "管理员", "管理员")),
	CUSTOMER(new Role(null, "customer", "用户", "用户")),
	INTERACTIVE(new Role(null, "interactive", "interactive调用权限", "interactive调用权限"));

	private Role role;

	RoleEnum(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
