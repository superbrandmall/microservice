package com.sbm.module.common.authorization.base.user.biz;

import com.sbm.module.common.authorization.base.user.domain.TCUser;
import com.sbm.module.common.data.biz.IDataService;

public interface ITCUserService extends IDataService<TCUser, Integer> {

	TCUser newInstance();

	TCUser findOneByCode(String code);

	TCUser findOneByUsername(String username);

	TCUser findOneByEmail(String email);

	TCUser findOneByMobile(String mobile);

}
