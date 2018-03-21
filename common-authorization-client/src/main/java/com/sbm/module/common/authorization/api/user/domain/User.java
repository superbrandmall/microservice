package com.sbm.module.common.authorization.api.user.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class User {

	@ApiModelProperty(value = "编号")
	private String code;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "手机")
	private String mobile;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "最后登录时间")
	private Date lastLogin;

	@ApiModelProperty(value = "邮箱是否验证")
	private Integer emailVerified;

	@ApiModelProperty(value = "手机是否验证")
	private Integer mobileVerified;

	@ApiModelProperty(value = "用户参数")
	private UserSettings settings = new UserSettings();

	public User(String code, String email, String mobile, String password, Date lastLogin, Integer emailVerified, Integer mobileVerified, UserSettings settings) {
		this.code = code;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.lastLogin = lastLogin;
		this.emailVerified = emailVerified;
		this.mobileVerified = mobileVerified;
		this.settings = settings;
	}

	public User() {

	}
}
