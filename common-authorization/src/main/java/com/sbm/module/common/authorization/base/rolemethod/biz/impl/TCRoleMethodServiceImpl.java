package com.sbm.module.common.authorization.base.rolemethod.biz.impl;

import com.sbm.module.common.authorization.base.rolemethod.biz.ITCRoleMethodService;
import com.sbm.module.common.authorization.base.rolemethod.domain.TCRoleMethod;
import com.sbm.module.common.authorization.base.rolemethod.repository.ITCRoleMethodRepository;
import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class TCRoleMethodServiceImpl extends DataServiceImpl<TCRoleMethod, Integer> implements ITCRoleMethodService {

	@Autowired
	private ITCRoleMethodRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TCRoleMethod> findAllByRoleCode(String roleCode) {
		return repository.findAllByRoleCode(roleCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TCRoleMethod> findAllByMethodCode(String methodCode) {
		return repository.findAllByMethodCode(methodCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCRoleMethod findOneByRoleCodeAndMethodCode(String roleCode, String methodCode) {
		return repository.findOneByRoleCodeAndMethodCode(roleCode, methodCode);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TCRoleMethod> findAllByRoleCodeInAndMethodCode(Collection<String> roleCodes, String methodCode) {
		return repository.findAllByRoleCodeInAndMethodCode(roleCodes, methodCode);
	}
}
