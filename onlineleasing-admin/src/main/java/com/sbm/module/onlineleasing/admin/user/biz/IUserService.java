package com.sbm.module.onlineleasing.admin.user.biz;

import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.onlineleasing.domain.user.UserSimple;

public interface IUserService {


	User findUserByUserCode(String userCode);

	UserSimple findOneByCodeSimple(String code);

}
