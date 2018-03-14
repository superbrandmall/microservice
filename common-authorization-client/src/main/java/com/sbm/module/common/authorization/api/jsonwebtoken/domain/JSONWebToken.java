package com.sbm.module.common.authorization.api.jsonwebtoken.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;

@Data
public class JSONWebToken {

	@NotBlank
	private String login;

	private Map<String, Object> claims;

}
