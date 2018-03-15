package com.sbm.module.common.authorization.base.rolemethod.biz;

import com.sbm.module.common.authorization.base.rolemethod.domain.TCRoleMethod;
import com.sbm.module.common.data.biz.IDataService;

import java.util.Collection;
import java.util.List;

public interface ITCRoleMethodService extends IDataService<TCRoleMethod, Integer> {

	List<TCRoleMethod> findAllByRoleCode(String roleCode);

	List<TCRoleMethod> findAllByMethodCode(String methodCode);

	TCRoleMethod findOneByRoleCodeAndMethodCode(String roleCode, String methodCode);

	List<TCRoleMethod> findAllByRoleCodeInAndMethodCode(Collection<String> roleCodes, String methodCode);

}
