package com.sbm.module.common.authorization.api.jsonwebtoken.biz.impl;

import com.sbm.module.common.authorization.api.jsonwebtoken.biz.IJSONWebTokenService;
import com.sbm.module.common.authorization.api.jsonwebtoken.domain.JSONWebToken;
import com.sbm.module.common.authorization.api.jsonwebtoken.provider.JSONWebTokenProvider;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JSONWebTokenServiceImpl extends CommonServiceImpl implements IJSONWebTokenService {

	@Autowired
	private JSONWebTokenProvider util;

	@Override
	public String token(JSONWebToken jsonWebToken) {
		return util.token(jsonWebToken);
	}

	@Override
	public Jws<Claims> parse(String token) {
		return util.parse(token);
	}

	@Override
	public void valid(String login, String token) {
		util.valid(login, token);
	}
}
