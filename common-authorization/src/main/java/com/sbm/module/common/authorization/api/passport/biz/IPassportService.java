package com.sbm.module.common.authorization.api.passport.biz;


import com.sbm.module.common.authorization.api.user.domain.User;

public interface IPassportService {

	User login(String username, String password);

	void updateLastLogin(String code);

}
