package com.sbm.module.onlineleasing.customer.login.biz.impl;

import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.onlineleasing.customer.register.biz.impl.RegisterCommonServiceImpl;
import com.sbm.module.onlineleasing.domain.login.LoginResult;

import javax.servlet.http.HttpServletResponse;

public abstract class LoginCommonServiceImpl extends RegisterCommonServiceImpl {

	/**
	 * 获取登录信息
	 *
	 * @param user
	 * @return
	 */
	protected abstract LoginResult getLoginResult(User user);

	/**
	 * 登录
	 *
	 * @param user
	 * @param response
	 * @return
	 */
	protected LoginResult login(User user, HttpServletResponse response) {
		// 获取登录信息
		LoginResult login = getLoginResult(user);
		// 修改最后登陆时间
		userService.updateLastLogin(user.getCode());
		// 写入头参数
		setHeader(response, user);
		return login;
	}

}
