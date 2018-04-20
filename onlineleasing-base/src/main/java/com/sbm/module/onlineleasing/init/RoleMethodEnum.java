package com.sbm.module.onlineleasing.init;

public enum RoleMethodEnum {

	CUSTOMER("customer", "onlineleasing-customer"),
	INTERACTIVE("interactive", "onlineleasing-interactive");

	private String role;

	private String applicationName;

	RoleMethodEnum(String role, String applicationName) {
		this.role = role;
		this.applicationName = applicationName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
}
