package com.sbm.module.onlineleasing.customer.login.biz;

import com.sbm.module.onlineleasing.domain.login.LoginResult;
import com.sbm.module.onlineleasing.domain.login.LoginSimple;

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
	LoginResult login(String username, String password, HttpServletResponse response);

	/**
	 * 登录简单版
	 *
	 * @param vo
	 * @param response
	 * @return
	 */
	LoginResult loginSimple(LoginSimple vo, HttpServletResponse response);

}
