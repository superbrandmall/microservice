package com.sbm.module.onlineleasing.customer.login.biz;

import com.sbm.module.onlineleasing.customer.login.domain.Login;

public interface ILoginService {

	/**
	 * 登录
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	Login login(String username, String password);

}
