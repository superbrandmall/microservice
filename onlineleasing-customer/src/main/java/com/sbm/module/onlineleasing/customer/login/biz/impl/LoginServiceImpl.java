package com.sbm.module.onlineleasing.customer.login.biz.impl;

import com.sbm.module.common.authorization.api.jsonwebtoken.client.IJSONWebTokenClient;
import com.sbm.module.common.authorization.api.jsonwebtoken.constant.JSONWebTokenConstant;
import com.sbm.module.common.authorization.api.jsonwebtoken.domain.JSONWebToken;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.login.biz.ILoginService;
import com.sbm.module.onlineleasing.customer.user.biz.IUserService;
import com.sbm.module.onlineleasing.domain.login.Login;
import com.sbm.module.onlineleasing.domain.user.UserMerchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl extends CommonServiceImpl implements ILoginService {

	@Autowired
	private IJSONWebTokenClient jsonWebTokenClient;

	@Autowired
	private IUserService userService;

	@Override
	public Login login(String username, String password, HttpServletResponse response) {
		User user = userService.login(username, password);
		Login login = new Login();
		// 用户编号
		login.setCode(user.getCode());
		// 邮箱
		login.setEmail(user.getEmail());
		// 手机
		login.setMobile(user.getMobile());
		if (null != user.getSettings()) {
			// 语言
			login.setLang(user.getSettings().getLang());
			// 境内境外
			login.setInternational(user.getSettings().getInternational());
		}
		// 用户商户关联关系
		UserMerchant userMerchant = userService.getUserMerchant(user.getCode());
		// 商户编号
		login.setMerchantCode(userMerchant.getMerchantCode());
		// 商户名称
		login.setMerchantName(userMerchant.getMerchantName());
		// 商户品牌数量
		login.setMerchantBrandCount(userMerchant.getMerchantBrandCount());

		// 修改最后登陆时间
		userService.updateLastLogin(user.getCode());
		// 写入头参数
		JsonContainer<String> token = jsonWebTokenClient.token(new JSONWebToken(user.getCode()));
		response.setHeader(JSONWebTokenConstant.AUTHORIZATION, checkJsonContainer(token));
		response.setHeader(JSONWebTokenConstant.LOGIN, user.getCode());
		return login;
	}
}
