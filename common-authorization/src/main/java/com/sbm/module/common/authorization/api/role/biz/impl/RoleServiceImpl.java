package com.sbm.module.common.authorization.api.role.biz.impl;

import com.sbm.module.common.authorization.api.api.role.domain.Role;
import com.sbm.module.common.authorization.api.role.biz.IRoleService;
import com.sbm.module.common.authorization.base.role.biz.ITCRoleService;
import com.sbm.module.common.authorization.base.role.domain.TCRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private ITCRoleService service;

	@Override
	public void save(Role vo) {
		TCRole po = service.findOneByCode(vo.getCode());
		if (null == po) {
			po = service.newInstance();
		}
		po.setRole(vo.getRole());
		po.setRoleName(vo.getRoleName());
		po.setRemark(vo.getRemark());
		service.save(po);
	}
}
