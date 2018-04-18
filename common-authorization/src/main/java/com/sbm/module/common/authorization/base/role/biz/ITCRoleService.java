package com.sbm.module.common.authorization.base.role.biz;

import com.sbm.module.common.authorization.base.role.domain.TCRole;
import com.sbm.module.common.data.biz.IDataService;

public interface ITCRoleService extends IDataService<TCRole, Integer> {

	TCRole newInstance();

	TCRole findOneByCode(String code);

	TCRole findOneByRole(String role);

}
