package com.sbm.module.onlineleasing.customer.register.biz.impl;

import com.sbm.module.common.authorization.api.jsonwebtoken.client.IJSONWebTokenClient;
import com.sbm.module.common.authorization.api.jsonwebtoken.constant.JSONWebTokenConstant;
import com.sbm.module.common.authorization.api.jsonwebtoken.domain.JSONWebToken;
import com.sbm.module.common.authorization.api.passport.client.IPassportClient;
import com.sbm.module.common.authorization.api.passport.domain.Register;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.register.biz.IRegisterService;
import com.sbm.module.onlineleasing.domain.register.StepOne;
import com.sbm.module.onlineleasing.domain.register.StepOneResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class RegisterServiceImpl extends CommonServiceImpl implements IRegisterService {

	@Autowired
	private IPassportClient client;
	@Autowired
	private IJSONWebTokenClient jsonWebTokenClient;

	@Override
	public StepOneResult stepOne(StepOne vo, HttpServletResponse response) {
		JsonContainer<User> result = client.register(new Register(vo.getEmail(), vo.getMobile(), vo.getPassword(), vo.getLang(), vo.getInternational()));
		User user = checkJsonContainer(result);

		// 修改最后登陆时间
		client.updateLastLogin(user.getCode());
		// 写入头参数
		JsonContainer<String> token = jsonWebTokenClient.token(new JSONWebToken(user.getCode()));
		response.setHeader(JSONWebTokenConstant.AUTHORIZATION, checkJsonContainer(token));
		response.setHeader(JSONWebTokenConstant.LOGIN, user.getCode());

		return mapOneIfNotNull(user, e -> new StepOneResult(e.getCode(), e.getEmail(), e.getMobile(), e.getSettings().getInternational()));
	}
}
