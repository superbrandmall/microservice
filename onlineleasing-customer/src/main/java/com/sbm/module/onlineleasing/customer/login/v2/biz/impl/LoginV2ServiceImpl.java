package com.sbm.module.onlineleasing.customer.login.v2.biz.impl;

import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.onlineleasing.customer.login.biz.impl.LoginCommonServiceImpl;
import com.sbm.module.onlineleasing.customer.login.v2.biz.ILoginV2Service;
import com.sbm.module.onlineleasing.customer.user.v2.biz.IUserInfoService;
import com.sbm.module.onlineleasing.domain.login.LoginResult;
import com.sbm.module.onlineleasing.domain.login.LoginSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class LoginV2ServiceImpl extends LoginCommonServiceImpl implements ILoginV2Service {

	@Autowired
	private IUserInfoService userInfoService;

	@Override
	@Transactional
	public LoginResult login(LoginSimple vo, HttpServletResponse response) {
		// 检查验证码
		checkVerified(vo.getVerificationCodeCheck(), vo.getUsername());
		// 登录（简单版）
		User user = userService.loginSimple(vo.getUsername());
		return login(user, response);
	}

	protected LoginResult getLoginResult(User user) {
		LoginResult login = new LoginResult();
		userInfoService.copyUserMerchant(userInfoService.getUserMerchant(user.getCode()), login);
		return login;
	}

}
