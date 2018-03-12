package com.sbm.module.common.authorization.api.role.biz.impl;

import com.sbm.module.common.authorization.api.role.biz.IRoleService;
import com.sbm.module.common.authorization.api.role.domain.Role;
import com.sbm.module.common.authorization.base.role.biz.ITCRoleService;
import com.sbm.module.common.authorization.base.role.domain.TCRole;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl extends CommonServiceImpl implements IRoleService {

	@Autowired
	private ITCRoleService service;

	@Override
	@Transactional
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

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Role> findAll(Pageable pageable) {
		return service.findAll(pageable).map(e -> new Role(e.getCode(), e.getRole(), e.getRoleName(), e.getRemark()));
	}
}
