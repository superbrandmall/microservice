package com.sbm.module.common.authorization.api.user.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserSettings {

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "语言")
	private Integer lang;

	@ApiModelProperty(value = "境内境外")
	private Integer international;

	public UserSettings(String name, Integer lang, Integer international) {
		this.name = name;
		this.lang = lang;
		this.international = international;
	}

	public UserSettings() {

	}
}
