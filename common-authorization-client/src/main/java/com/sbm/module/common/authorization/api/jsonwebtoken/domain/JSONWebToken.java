package com.sbm.module.common.authorization.api.jsonwebtoken.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;

@Data
public class JSONWebToken {

	@ApiModelProperty(value = "用户编号")
	@NotBlank
	private String login;

	@ApiModelProperty(value = "其他参数")
	private Map<String, Object> claims;

	public JSONWebToken(String login, Map<String, Object> claims) {
		this.login = login;
		this.claims = claims;
	}

	public JSONWebToken(String login) {

		this.login = login;
	}

	public JSONWebToken() {

	}
}
