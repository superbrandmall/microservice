package com.sbm.module.common.authorization.api.rolemethod.biz.impl;

import com.sbm.module.common.authorization.api.rolemethod.biz.IRoleMethodService;
import com.sbm.module.common.authorization.api.rolemethod.domain.RoleMethod;
import com.sbm.module.common.authorization.base.rolemethod.biz.ITCRoleMethodService;
import com.sbm.module.common.authorization.base.rolemethod.domain.TCRoleMethod;
import com.sbm.module.common.authorization.exception.AuthorizationCode;
import com.sbm.module.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleMethodServiceImpl implements IRoleMethodService {

	@Autowired
	private ITCRoleMethodService service;

	@Override
	public void save(RoleMethod vo) {
		TCRoleMethod po = service.findOneByRoleCodeAndMethodCode(vo.getRoleCode(), vo.getMethodCode());
		if (null != po) {
			throw new BusinessException(AuthorizationCode.RM0001, new Object[]{vo.getRoleCode(), vo.getMethodCode()});
		}

	}
}
