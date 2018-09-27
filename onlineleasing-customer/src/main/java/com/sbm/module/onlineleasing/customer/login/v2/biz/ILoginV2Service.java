package com.sbm.module.onlineleasing.customer.login.v2.biz;

import com.sbm.module.onlineleasing.domain.login.LoginResult;
import com.sbm.module.onlineleasing.domain.login.LoginSimple;

import javax.servlet.http.HttpServletResponse;

public interface ILoginV2Service {

	/**
	 * 登录
	 *
	 * @param vo
	 * @param response
	 * @return
	 */
	LoginResult login(LoginSimple vo, HttpServletResponse response);

}
