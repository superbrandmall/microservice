package com.sbm.module.common.authorization.base.userrole.biz.impl;

import com.sbm.module.common.authorization.base.userrole.biz.ITCUserRoleService;
import com.sbm.module.common.authorization.base.userrole.domain.TCUserRole;
import com.sbm.module.common.authorization.base.userrole.repository.ITCUserRoleRepository;
import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TCUserRoleServiceImpl extends DataServiceImpl<TCUserRole, Integer> implements ITCUserRoleService {

	@Autowired
	private ITCUserRoleRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TCUserRole> findAllByUserCode(String userCode) {
		return repository.findAllByUserCode(userCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TCUserRole> findAllByRoleCode(String roleCode) {
		return repository.findAllByRoleCode(roleCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCUserRole findOneByUserCodeAndRoleCode(String roleCode, String methodCode) {
		return repository.findOneByUserCodeAndRoleCode(roleCode, methodCode);
	}
}
