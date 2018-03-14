package com.sbm.module.common.authorization.base.userrole.biz;

import com.sbm.module.common.authorization.base.userrole.domain.TCUserRole;
import com.sbm.module.common.data.biz.IDataService;

public interface ITCUserRoleService extends IDataService<TCUserRole, Integer> {

	TCUserRole findAllByUserCode(String userCode);

	TCUserRole findAllByRoleCode(String roleCode);

	TCUserRole findOneByUserCodeAndRoleCode(String userCode, String roleCode);

}
