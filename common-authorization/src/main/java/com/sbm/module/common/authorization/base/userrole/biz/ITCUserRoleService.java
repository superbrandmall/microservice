package com.sbm.module.common.authorization.base.userrole.biz;

import com.sbm.module.common.authorization.base.userrole.domain.TCUserRole;
import com.sbm.module.common.data.biz.IDataService;

import java.util.List;

public interface ITCUserRoleService extends IDataService<TCUserRole, Integer> {

	List<TCUserRole> findAllByUserCode(String userCode);

	List<TCUserRole> findAllByRoleCode(String roleCode);

	TCUserRole findOneByUserCodeAndRoleCode(String userCode, String roleCode);

	void deleteByUserCode(String userCode);
}
