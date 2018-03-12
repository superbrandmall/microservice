package com.sbm.module.common.authorization.api.rolemethod.biz.impl;

import com.sbm.module.common.authorization.api.rolemethod.biz.IRoleMethodService;
import com.sbm.module.common.authorization.api.rolemethod.domain.RoleMethod;
import com.sbm.module.common.authorization.base.rolemethod.biz.ITCRoleMethodService;
import com.sbm.module.common.authorization.base.rolemethod.domain.TCRoleMethod;
import com.sbm.module.common.authorization.exception.AuthorizationCode;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleMethodServiceImpl extends CommonServiceImpl implements IRoleMethodService {

	@Autowired
	private ITCRoleMethodService service;

	@Override
	@Transactional
	public void save(List<RoleMethod> vos) {
		vos.forEach(e -> {
			TCRoleMethod po = service.findOneByRoleCodeAndMethodCode(e.getRoleCode(), e.getMethodCode());
			if (null != po) {
				throw new BusinessException(AuthorizationCode.RM0001, new Object[]{e.getRoleCode(), e.getMethodCode()});
			}
			po = new TCRoleMethod(e.getRoleCode(), e.getMethodCode());
			service.save(po);
		});
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<RoleMethod> findAll(Pageable pageable) {
		return service.findAll(pageable).map(e -> new RoleMethod(e.getRoleCode(), e.getMethodCode()));
	}
}
