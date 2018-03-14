package com.sbm.module.common.authorization.api.jsonwebtoken.biz;


import com.sbm.module.common.authorization.api.jsonwebtoken.domain.JSONWebToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface IJSONWebTokenService {

	String token(JSONWebToken jsonWebToken);

	Jws<Claims> parse(String token);

	Boolean valid(String login, String token);

}
