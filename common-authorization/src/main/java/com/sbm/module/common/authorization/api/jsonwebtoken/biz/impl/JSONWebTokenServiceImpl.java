package com.sbm.module.common.authorization.api.jsonwebtoken.biz.impl;

import com.sbm.module.common.authorization.api.jsonwebtoken.biz.IJSONWebTokenService;
import com.sbm.module.common.authorization.api.jsonwebtoken.domain.JSONWebToken;
import com.sbm.module.common.authorization.exception.AuthorizationCode;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JSONWebTokenServiceImpl extends CommonServiceImpl implements IJSONWebTokenService {

	/**
	 * 版本
	 */
	@Value("jwt.tokenVersion")
	public String tokenVersion;

	/**
	 * 发行人
	 */
	@Value("jwt.issuer")
	public String issuer;

	/**
	 * 抽象主题
	 */
	@Value("jwt.subject")
	public String subject;

	/**
	 * Time To Live
	 */
	public Long ttl;

	@Value("${jwt.ttl}")
	public void setTtl(String ttl) {
		this.ttl = Long.valueOf(ttl) * 1000L;
	}

	/**
	 * 加密方式
	 */
	private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	/**
	 * 签名密钥
	 */
	public Key signingKey;

	@Value("jwt.hs256key")
	public void setSigningKey(String hs256key) {
		this.signingKey = new SecretKeySpec(Base64.decodeBase64(hs256key), signatureAlgorithm.getJcaName());
	}

	@Override
	public String token(JSONWebToken jsonWebToken) {
		Long now = System.currentTimeMillis();
		Long exp = now + ttl;

		Map<String, Object> claims = new HashMap<>();

		claims.put(Claims.ID, tokenVersion);
		claims.put(Claims.ISSUER, issuer);
		claims.put(Claims.SUBJECT, subject);
		claims.put(Claims.AUDIENCE, jsonWebToken.getLogin());
		claims.put(Claims.ISSUED_AT, new Date(now));
		claims.put(Claims.EXPIRATION, new Date(exp));
		// 加入其他条件
		if (null != jsonWebToken.getClaims()) {
			claims.putAll(jsonWebToken.getClaims());
		}

		JwtBuilder jwtBuilder = Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.setClaims(claims).signWith(signatureAlgorithm, signingKey);
		return jwtBuilder.compact();
	}

	public Jws<Claims> parse(String token) {
		Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token.trim());
		return jwsClaims;
	}

	@Override
	public Boolean valid(String login, String token) {
		Jws<Claims> jwsClaims;
		try {
			jwsClaims = parse(token);
		} catch (Exception e) {
			// 解析异常
			throw new BusinessException(AuthorizationCode.JWT0001, e);
		}
		// 用户login和token不匹配
		if (!login.equals(jwsClaims.getBody().getAudience())) {
			throw new BusinessException(AuthorizationCode.JWT0003);
		}

		if (null != jwsClaims) {
			Long exp = (Long) jwsClaims.getBody().get(Claims.EXPIRATION);
			Long now = System.currentTimeMillis();
			// 超时
			if (exp < now) {
				throw new BusinessException(AuthorizationCode.JWT0002);
			}
		}
		return true;
	}
}
