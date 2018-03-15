package com.sbm.module.common.authorization.api.userrole.biz.impl;

import com.sbm.module.common.authorization.api.userrole.biz.IUserRoleService;
import com.sbm.module.common.authorization.api.userrole.domain.UserRole;
import com.sbm.module.common.authorization.base.userrole.biz.ITCUserRoleService;
import com.sbm.module.common.authorization.base.userrole.domain.TCUserRole;
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
public class UserRoleServiceImpl extends CommonServiceImpl implements IUserRoleService {

	@Autowired
	private ITCUserRoleService service;

	@Override
	@Transactional
	public void save(List<UserRole> vos) {
		vos.forEach(e -> {
			TCUserRole po = service.findOneByUserCodeAndRoleCode(e.getUserCode(), e.getRoleCode());
			if (null != po) {
				throw new BusinessException(AuthorizationCode.UR0001, new Object[]{e.getUserCode(), e.getRoleCode()});
			}
			po = new TCUserRole(e.getUserCode(), e.getRoleCode());
			service.save(po);
		});
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<UserRole> findAll(Pageable pageable) {
		return service.findAll(pageable).map(e -> new UserRole(e.getUserCode(), e.getRoleCode()));
	}


	public List<UserRole> findAllByUserCode(String userCode) {
		return map(service.findAllByUserCode(userCode), e -> new UserRole(e.getUserCode(), e.getRoleCode()));
	}

}
