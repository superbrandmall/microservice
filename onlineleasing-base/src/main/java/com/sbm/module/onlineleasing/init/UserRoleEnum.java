package com.sbm.module.onlineleasing.init;

public enum UserRoleEnum {
	ADMIN("onlineleasing@superbrandmall.com", "12345678", "admin");

	private String email;

	private String password;

	private String role;

	UserRoleEnum(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
