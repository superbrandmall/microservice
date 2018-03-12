package com.sbm.module.common.authorization.base.rolemethod.biz;

import com.sbm.module.common.authorization.base.rolemethod.domain.TCRoleMethod;
import com.sbm.module.common.data.biz.IDataService;

public interface ITCRoleMethodService extends IDataService<TCRoleMethod, Integer> {

	TCRoleMethod findAllByRoleCode(String roleCode);

	TCRoleMethod findAllByMethodCode(String methodCode);

	TCRoleMethod findOneByRoleCodeAndMethodCode(String roleCode, String methodCode);

}
