package com.sbm.module.onlineleasing.customer.login.biz;

import com.sbm.module.onlineleasing.customer.login.domain.Login;

import javax.servlet.http.HttpServletResponse;

public interface ILoginService {

	/**
	 * 登录
	 *
	 * @param username
	 * @param password
	 * @param response
	 * @return
	 */
	Login login(String username, String password, HttpServletResponse response);

}
